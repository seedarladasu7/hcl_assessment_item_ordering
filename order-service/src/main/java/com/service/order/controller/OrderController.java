package com.service.order.controller;

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

import com.service.order.client.ItemsClient;
import com.service.order.dto.ItemDTO;
import com.service.order.dto.OrderDTO;
import com.service.order.dto.OrderRequest;
import com.service.order.exceptions.OrderNotFoundException;
import com.service.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	ItemsClient itemsClient;

	@Autowired
	OrderService orderSer;

	@GetMapping("")
	public ResponseEntity<List<OrderDTO>> getOrders() {
		List<OrderDTO> orders = orderSer.getAllOrders();

		if (orders == null || orders.isEmpty())
			throw new OrderNotFoundException("Orders are not available in this service...");

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable("orderId") Integer orderId) {
		OrderDTO itemDTO = orderSer.getOrder(orderId);

		if (itemDTO == null)
			throw new OrderNotFoundException("Order is not available with id: " + orderId);

		return new ResponseEntity<>(itemDTO, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderReq) {
		orderSer.saveOrder(orderReq);
		return new ResponseEntity<>("Order has been created successfully...", HttpStatus.ACCEPTED);
	}

	
	
	
	
	
	// Start: Testing purpose::::Item Service call through Feign client

	@GetMapping("/items")
	public ResponseEntity<List<ItemDTO>> getAllItems() {
		List<ItemDTO> items = itemsClient.getItems();

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping("/items/{itemId}")
	public ResponseEntity<ItemDTO> getItemDetails(@PathVariable("itemId") Integer itemId) {
		ItemDTO item = itemsClient.getItemDetails(itemId);

		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@PostMapping("/items")
	public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO) {
		String response = itemsClient.createItem(itemDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/items/selectedItems/{itemIds}")
	public ResponseEntity<List<ItemDTO>> getSelectedItems(@PathVariable("itemIds") List<Integer> itemIds) {
		List<ItemDTO> items = itemsClient.getSelectedItems(itemIds);
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	// End: Testing purpose::::Item Service call through Feign client

}
