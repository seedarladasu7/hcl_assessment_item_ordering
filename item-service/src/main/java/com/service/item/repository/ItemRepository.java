package com.service.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.item.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public List<Item> findByIdIn(List<Integer> ids);

}
