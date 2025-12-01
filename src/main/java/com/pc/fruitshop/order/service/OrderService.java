package com.pc.fruitshop.order.service;

import com.pc.fruitshop.order.dto.OrderResult;

/*
 * [ OrderService ]
 * 負責描述 訂單相關的商業邏輯規格
 * 
 * */

public interface OrderService {

    // 數量正規化
    int normalizeQty(Integer qty);

    // 處理整筆訂單 (含庫存檢查+扣庫存+計算金額)
    OrderResult processOrder(
            String customerName,
            int appleQty,
            int bananaQty,
            int watermelonQty
    );

}
