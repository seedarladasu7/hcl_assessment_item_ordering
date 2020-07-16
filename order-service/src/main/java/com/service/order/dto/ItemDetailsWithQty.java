package com.service.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsWithQty {

	private ItemDTO orderItem;
	private int itemQty;

}
