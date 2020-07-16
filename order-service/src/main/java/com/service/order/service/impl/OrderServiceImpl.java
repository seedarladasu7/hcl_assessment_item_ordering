package com.service.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.order.client.ItemsClient;
import com.service.order.dto.ItemDTO;
import com.service.order.dto.ItemDetailsWithQty;
import com.service.order.dto.ItemQty;
import com.service.order.dto.OrderDTO;
import com.service.order.dto.OrderRequest;
import com.service.order.entity.CustOrder;
import com.service.order.entity.OrderItems;
import com.service.order.exceptions.ItemNotFoundException;
import com.service.order.repository.CustOrderRepository;
import com.service.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CustOrderRepository orderRepo;

	@Autowired
	ItemsClient itemsClient;

	SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void saveOrder(OrderRequest orderReq) {

		float totAmount = 0.0f;

		List<ItemQty> itemAndQty = orderReq.getItemAndQty();

		if (itemAndQty != null && !itemAndQty.isEmpty()) {

			CustOrder order = new CustOrder();

			Set<OrderItems> orderItemSet = new HashSet<>();

			for (ItemQty itemQty : itemAndQty) {

				OrderItems orderItems = new OrderItems();

				ItemDTO item = itemsClient.getItemDetails(itemQty.getItemId());

				if (item == null) {
					throw new ItemNotFoundException("Item not found with id:" + itemQty.getItemId());
				}
				orderItems.setItemId(itemQty.getItemId());
				orderItems.setQty(itemQty.getItemQty());

				float itemQtyPrice = item.getPrice() * itemQty.getItemQty();

				totAmount += itemQtyPrice;
				orderItems.setItemQtyPrice(itemQtyPrice);

				orderItemSet.add(orderItems);

			}

			order.setCustName(orderReq.getCustName());
			order.setOrderDate(simpDate.format(new Date()));
			order.setOrderTotPrice(totAmount);
			order.setOrderItems(orderItemSet);

			orderRepo.save(order);
		}

	}

	@Override
	public List<OrderDTO> getAllOrders() {

		List<CustOrder> orders = orderRepo.findAll();
		List<OrderDTO> ordersList = null;
		if (!orders.isEmpty()) {
			ordersList = new ArrayList<>();

			for (CustOrder order : orders) {
				OrderDTO orderDTO = getOrderDetails(order);
				ordersList.add(orderDTO);
			}
		}
		return ordersList;
	}

	@Override
	public OrderDTO getOrder(int orderId) {

		OrderDTO orderDTO = null;
		Optional<CustOrder> orderOptnl = orderRepo.findById(orderId);

		if (orderOptnl.isPresent()) {

			CustOrder order = orderOptnl.get();

			orderDTO = getOrderDetails(order);

		}
		return orderDTO;
	}

	private OrderDTO getOrderDetails(CustOrder order) {
		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setOrderId(order.getId());
		orderDTO.setCustName(order.getCustName());
		orderDTO.setOrderDate(order.getOrderDate());

		List<ItemDetailsWithQty> orderItems = new ArrayList<>();

		Set<OrderItems> itemsSet = order.getOrderItems();

		if (itemsSet != null && !itemsSet.isEmpty()) {
			itemsSet.stream().forEach(orderedItem -> {
				ItemDTO item = itemsClient.getItemDetails(orderedItem.getItemId());
				ItemDetailsWithQty itemDetWithQty = new ItemDetailsWithQty();
				itemDetWithQty.setOrderItem(item);
				itemDetWithQty.setItemQty(orderedItem.getQty());
				orderItems.add(itemDetWithQty);
			});
		}

		orderDTO.setOrderItems(orderItems);
		orderDTO.setTotPrice(order.getOrderTotPrice());

		return orderDTO;
	}

}
