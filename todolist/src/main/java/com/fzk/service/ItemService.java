package com.fzk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fzk.Global;
import com.fzk.dao.ItemDao;
import com.fzk.entity.Item;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	/**
	 * 插入一条记录
	 */
	public boolean addItem(Item item) {
		item.preInsert();
		return itemDao.insert(item) == 1 ? true : false;
	}

	/**
	 * 查询事项信息
	 */
	public List<Item> findList(Item item) {
		return itemDao.findList(item);
	}

	/**
	 * 将指定id的事项设为已完成
	 */
	public void finishItem(String id) {
		Item item = new Item();
		item.setId(id);
		item.setFinishflag(Global.FINISHFLAG_YES); // 设置完成标记
		//item.setCreatetime(new Date());
		//item.setFinishtime(new Date()); // 设置完成时间
		itemDao.update(item);
	}

	/**
	 * 将指定id的事项置顶
	 */
	public void topItem(String id) {
		Item item = new Item();
		item.setId(id);
		item.setLastmodifytime(new Date()); // 更新最近修改时间
		itemDao.update(item);
	}
}