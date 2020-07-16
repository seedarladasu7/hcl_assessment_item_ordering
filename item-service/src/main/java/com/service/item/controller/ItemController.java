package com.service.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.item.dto.ItemDTO;
import com.service.item.exceptions.ItemNotFoundException;
import com.service.item.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	ItemService itemSer;

	@GetMapping("")
	public ResponseEntity<List<ItemDTO>> getItems() {
		List<ItemDTO> items = itemSer.getAllItems(null);

		if (items == null || items.isEmpty())
			throw new ItemNotFoundException("Items are not available in this service...");

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ItemDTO> getItemDetails(@PathVariable("itemId") Integer itemId) {
		ItemDTO itemDTO = itemSer.getItem(itemId);
		if (itemDTO == null)
			throw new ItemNotFoundException("Item is not available with id: " + itemId);

		return new ResponseEntity<>(itemDTO, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO) {
		itemSer.saveItem(itemDTO);
		return new ResponseEntity<>("Item has been created successfully...", HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/selectedItems/{itemIds}")
	public ResponseEntity<List<ItemDTO>> getSelectedItems(@PathVariable("itemIds") List<Integer> itemIds) {
		List<ItemDTO> items = itemSer.getAllItems(itemIds);

		if (items == null || items.isEmpty())
			throw new ItemNotFoundException("Items are not available in this service...");

		return new ResponseEntity<>(items, HttpStatus.OK);		
	}
	
	

}
