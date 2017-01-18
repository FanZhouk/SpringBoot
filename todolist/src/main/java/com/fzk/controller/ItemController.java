package com.fzk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fzk.Global;
import com.fzk.entity.Item;
import com.fzk.service.ItemService;

/**
 * 事项Controller
 * 
 * @author fanzhoukai
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 查询所有待办事项
	 */
	@RequestMapping({ "", "index" })
	public String getData(ModelMap model) {
		Item item = new Item(); // 默认查询未删除、未完成事项
		item.setFinishflag(Global.FINISHFLAG_NO);
		item.setDeleteflag(Global.DELETEFLAG_NO);
		List<Item> list = itemService.findList(item);
		model.put("data", list);
		return "itemList";
	}

	/**
	 * 查询所有事项，包括已完成和未完成
	 */
	@RequestMapping("dataAll")
	public String dataAll(ModelMap model) {
		Item item = new Item(); // 查询未删除事项
		item.setDeleteflag(Global.DELETEFLAG_NO);
		List<Item> list = itemService.findList(item);
		model.put("data", list);
		return "itemList";
	}

	/**
	 * 跳转至添加事项页面
	 */
	@RequestMapping("addItemPage")
	public String addItemPage() {
		return "itemForm";
	}

	/**
	 * 添加事项
	 */
	@RequestMapping("addItem")
	public String addItem(Item item, ModelMap model) {
		boolean k = itemService.addItem(item);
		if (k)
			model.put("message", "创建成功");
		else
			model.put("message", "创建失败，请再试一次");
		return "redirect:/";
	}

	/**
	 * 完成事项
	 */
	@RequestMapping("finish")
	public String finish(String id) {
		itemService.finishItem(id);
		return "redirect:/";
	}

	/**
	 * 事项置顶
	 */
	@RequestMapping("top")
	public String top(String id) {
		itemService.topItem(id);
		return "redirect:/";
	}
}
