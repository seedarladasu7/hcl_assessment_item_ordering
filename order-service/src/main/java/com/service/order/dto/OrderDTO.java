package com.service.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private int orderId;
	private String custName;
	private String orderDate;
	private List<ItemDetailsWithQty> orderItems;
	private float totPrice;

}
