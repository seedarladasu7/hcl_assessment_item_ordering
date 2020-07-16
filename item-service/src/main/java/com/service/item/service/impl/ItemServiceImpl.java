package com.service.item.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.item.dto.ItemDTO;
import com.service.item.entity.Item;
import com.service.item.repository.ItemRepository;
import com.service.item.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepo;

	public void saveItem(ItemDTO itemDTO) {
		Item item = new Item();
		item.setName(itemDTO.getName());
		item.setCode(itemDTO.getCode());
		item.setQty(itemDTO.getQty());
		item.setPrice(itemDTO.getPrice());
		itemRepo.save(item);
	}

	public List<ItemDTO> getAllItems(List<Integer> itemIds) {
		
		List<Item> items = null;
		List<ItemDTO> itemsList = null;
		
		if(itemIds != null && itemIds.size() > 0) {
			items = itemRepo.findByIdIn(itemIds);
		} else {
			items = itemRepo.findAll();
		}
		
		if (items != null) {
			itemsList = items.stream().map(
					item -> new ItemDTO(item.getId(), item.getName(), item.getCode(), item.getQty(), item.getPrice()))
					.collect(Collectors.toList());

		}

		return itemsList;
	}

	public ItemDTO getItem(int itemId) {
		ItemDTO itemDTO = null;
		Optional<Item> itemOptnl = itemRepo.findById(itemId);

		if (itemOptnl.isPresent()) {
			itemDTO = new ItemDTO();
			Item item = itemOptnl.get();
			itemDTO.setCode(item.getCode());
			itemDTO.setName(item.getName());
			itemDTO.setQty(item.getQty());
			itemDTO.setPrice(item.getPrice());
			itemDTO.setItemId(item.getId());
		}
		return itemDTO;
	}

}
