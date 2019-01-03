package com.gzz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//贴吧顶贴表
@Entity
@Table(name="postbarGoodtb")
public class PostbarGood {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="int unsigned NOT NULL comment '贴吧顶贴ID'")
	@JsonProperty(value ="id")
	private Integer postbarGoodId;
	@JsonIgnore
	@ManyToOne(targetEntity = Postbar.class)
	@JoinColumn(name="postbar_good_postbar_id")
	private Postbar postbar;
	@Column(columnDefinition="int unsigned NOT NULL comment '备注：用户的id外键'")
	private Integer uId;
	public Integer getPostbarGoodId() {
		return postbarGoodId;
	}
	public void setPostbarGoodId(Integer postbarGoodId) {
		this.postbarGoodId = postbarGoodId;
	}
	public Postbar getPostbar() {
		return postbar;
	}
	public void setPostbar(Postbar postbar) {
		this.postbar = postbar;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	
}
