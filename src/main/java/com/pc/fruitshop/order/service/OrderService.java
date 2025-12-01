package com.pc.fruitshop.order.service;

import com.pc.fruitshop.order.dto.OrderRequest;
import com.pc.fruitshop.order.dto.OrderResponse;

/*
 * [ OrderService ]
 * 負責描述 訂單相關的商業邏輯規格
 * 
 * */

public interface OrderService {

    // ==========  Q3.3 的 API + 寫入資料庫版 ========== 
    OrderResponse processOrder(OrderRequest request);
    
}
