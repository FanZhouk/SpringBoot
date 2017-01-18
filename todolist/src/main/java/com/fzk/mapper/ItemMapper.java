package com.fzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.fzk.entity.Item;

/**
 * Item映射类
 * 
 * @author fanzhoukai
 * 
 */
public interface ItemMapper {

	/**
	 * 插入一条数据
	 */
	@Insert("INSERT INTO item(id,content,createtime,lastmodifytime,finishtime,finishflag,deleteflag) "
			+ "VALUES (#{id},#{content},#{createtime},#{lastmodifytime},#{finishtime},#{finishflag},#{deleteflag})")
	public int insert(Item item);

	// 查询所有未完成事项
	public List<Item> findList(Item item);

	// 更新记录
	public void update(Item item);
}
