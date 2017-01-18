package com.fzk.entity;

import java.util.Date;
import java.util.UUID;

/**
 * 事项实体
 * 
 * @author fanzhoukai
 * 
 */
public class Item {
	private String id;
	private String content;
	private Date createtime;
	private Date lastmodifytime;
	private Date finishtime;
	private Integer finishflag;
	private Integer deleteflag;

	/**
	 * 插入之前调用
	 * 
	 * 设置了主键id，创建时间，最后修改时间，完成标记和删除标记等自动生成的字段
	 */
	public void preInsert() {
		id = UUID.randomUUID().toString();
		lastmodifytime = createtime = new Date();
		finishflag = 0;
		deleteflag = 0;
	}

	public Item() {
	}

	public Item(String id, String content, Date createtime, Date finishtime,
			Integer finishflag, Integer deleteflag) {
		this.id = id;
		this.content = content;
		this.createtime = createtime;
		this.finishtime = finishtime;
		this.finishflag = finishflag;
		this.deleteflag = deleteflag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public Date getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public Integer getFinishflag() {
		return finishflag;
	}

	public void setFinishflag(Integer finishflag) {
		this.finishflag = finishflag;
	}

	public Integer getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}

}
