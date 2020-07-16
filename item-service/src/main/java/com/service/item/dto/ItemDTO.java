package com.service.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

	private int itemId;
	private String name;
	private String code;
	private int qty;
	private float price;

}
