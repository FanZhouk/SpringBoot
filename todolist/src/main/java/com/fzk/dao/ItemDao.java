package com.fzk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fzk.entity.Item;
import com.fzk.mapper.ItemMapper;

@Repository
public class ItemDao {
	@Autowired
	private ItemMapper itemMapper;

	public int insert(Item item) {
		return itemMapper.insert(item);
	}

	public List<Item> findList(Item item) {
		return itemMapper.findList(item);
	}

	public void update(Item item) {
		itemMapper.update(item);
	}
}
