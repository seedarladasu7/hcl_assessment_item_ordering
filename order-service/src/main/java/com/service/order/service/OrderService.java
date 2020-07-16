package com.service.order.service;

import java.util.List;

import com.service.order.dto.OrderDTO;
import com.service.order.dto.OrderRequest;

public interface OrderService {

	public void saveOrder(OrderRequest orderReq);

	public List<OrderDTO> getAllOrders();

	public OrderDTO getOrder(int orderId);

}
