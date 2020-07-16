package com.service.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.service.order.dto.ItemDTO;

@FeignClient(name = "http://items-app/ItemService/items")
public interface ItemsClient {

	@GetMapping("")
	public List<ItemDTO> getItems();

	@GetMapping("/{itemId}")
	public ItemDTO getItemDetails(@PathVariable("itemId") Integer itemId);

	@GetMapping(value = "/selectedItems/{itemIds}")
	public List<ItemDTO> getSelectedItems(@PathVariable("itemIds") List<Integer> itemIds);
	
	@PostMapping("")
	public String createItem(@RequestBody ItemDTO itemDTO);

}
