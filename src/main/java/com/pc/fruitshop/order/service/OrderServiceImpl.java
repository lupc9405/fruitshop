package com.pc.fruitshop.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pc.fruitshop.inventory.service.InventoryService;
import com.pc.fruitshop.order.dto.*;
import com.pc.fruitshop.order.model.OrderAttempt;
import com.pc.fruitshop.order.model.OrderItem;
import com.pc.fruitshop.order.model.OrderRecord;
import com.pc.fruitshop.order.repository.OrderAttemptRepository;
import com.pc.fruitshop.order.repository.OrderItemRepository;
import com.pc.fruitshop.order.repository.OrderRecordRepository;
import com.pc.fruitshop.product.model.Product;
import com.pc.fruitshop.product.service.ProductService;

/*
 * [ OrderServiceImpl ]
 * 負責實作 OrderService 的商業邏輯
 * 數量正規化、庫存檢查、扣庫存、金額計算
 * 
 * */

@Service
public class OrderServiceImpl implements OrderService {

	private final ProductService productService;
	private final InventoryService inventoryService;
	private final OrderRecordRepository orderRecordRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderAttemptRepository orderAttemptRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	// 建構子注入
	public OrderServiceImpl(ProductService productService, InventoryService inventoryService,
			OrderRecordRepository orderRecordRepository, OrderItemRepository orderItemRepository,
			OrderAttemptRepository orderAttemptRepository) {
		this.productService = productService;
		this.inventoryService = inventoryService;
		this.orderRecordRepository = orderRecordRepository;
		this.orderItemRepository = orderItemRepository;
		this.orderAttemptRepository = orderAttemptRepository;
	}

	// ========== Q3.3 的 API + 動態商品 + 寫入資料庫 ==========
	@Override
	@Transactional
	public OrderResponse processOrder(OrderRequest request) {

		List<OrderItemResponse> itemResponses = new ArrayList<>();
		StringBuilder errors = new StringBuilder();
		int totalAmount = 0;
		boolean hasError = false;

		// 初始成功回應 (若失敗後會改掉)
		OrderResponse response;

		// 庫存檢查

		for (OrderItemRequest item : request.getItems()) {
			int qty = item.getQty();

			if (qty == 0)
				continue; // 0 不需要檢查也不需要寫入

			Product product = productService.findById(item.getProductId());

			if (!inventoryService.hasEnoughStock(product.getId(), qty)) {
				hasError = true;
				errors.append(product.getName()).append(" 庫存不足<br>");
			}
		}

		// 失敗: 寫入log, 不寫入訂單
		if (hasError) {
			response = new OrderResponse(false, errors.toString(), 0, null);
			saveAttemptLog(request, response);
			return response;
		}

		// 成功: 建立訂單
		OrderRecord orderRecord = new OrderRecord();
		orderRecord.setCustomerName(request.getCustomerName());
		orderRecord.setCreatedAt(LocalDateTime.now());
		orderRecord.setTotalAmount(0);

		orderRecord = orderRecordRepository.save(orderRecord);

		// 扣庫存 + 寫入明細
		for (OrderItemRequest item : request.getItems()) {
			int qty = item.getQty();

			if (qty == 0)
				continue; // 不寫入明細

			Product product = productService.findById(item.getProductId());

			// 扣庫存
			inventoryService.decreaseStock(product.getId(), qty);

			// 建立明細
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderRecord(orderRecord);
			orderItem.setProduct(product);
			orderItem.setQuantity(qty);
			orderItemRepository.save(orderItem);

			// 回傳給前端的項目資訊
			OrderItemResponse resp = new OrderItemResponse(product.getId(), product.getName(), qty, product.getPrice(),
					product.getUnitName());

			itemResponses.add(resp);
			totalAmount += resp.getAmount();
		}

		// 更新總金額
		orderRecord.setTotalAmount(totalAmount);
		orderRecordRepository.save(orderRecord);

		// 回傳給前端
		StringBuilder msg = new StringBuilder();
		msg.append(request.getCustomerName()).append(" 你好，您總共購買：<br>");

		for (OrderItemResponse item : itemResponses) {
			msg.append(item.getProductName()).append(" ").append(item.getQty()).append(" ").append(item.getUnitName())
					.append("，小計 ").append(item.getAmount()).append(" 元<br>");
		}

		msg.append("<br>總計 ").append(totalAmount).append(" 元");

		response = new OrderResponse(true, msg.toString(), totalAmount, itemResponses);

		// 下單紀錄
		saveAttemptLog(request, response);

		return response;
	}

	// 紀錄下單行為 (成功,失敗)
	private void saveAttemptLog(OrderRequest request, OrderResponse response) {
		try {
			OrderAttempt log = new OrderAttempt();
			log.setCustomerName(request.getCustomerName());
			log.setSuccess(response.isSuccess());

			// 將 HTML <br> 換成純文字換行
			String plainMessage = response.getMessage().replace("<br>", "\n");
			log.setMessage(plainMessage);

			log.setRequestJson(objectMapper.writeValueAsString(request));
			log.setResponseJson(objectMapper.writeValueAsString(response));
			log.setCreatedAt(LocalDateTime.now());

			orderAttemptRepository.save(log);

		} catch (Exception ignored) {
		}
	}
}
