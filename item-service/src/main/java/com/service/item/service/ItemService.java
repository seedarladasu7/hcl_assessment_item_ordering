package com.service.item.service;

import java.util.List;

import com.service.item.dto.ItemDTO;

public interface ItemService {
	
	public void saveItem(ItemDTO itemDTO);
	
	public List<ItemDTO> getAllItems(List<Integer> itemIds);
	
	public ItemDTO getItem(int itemId);
}
