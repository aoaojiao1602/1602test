package com.lwj.springcloud.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "judgetb")
@GenericGenerator(name = "judgeId", strategy = "increment")
@Getter
@Setter
//判断题表
public class Judges {
	@Id // 指定主键 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int unsigned NOT NULL comment '备注:自动增长主键'")
	private Integer judgeId;
	@Column(columnDefinition="varchar(100) comment '备注:题目'")
	private String topic;
	@Column(columnDefinition="varchar(100)  comment '备注:答案'")
	private String answer;
	@Column(columnDefinition="int comment '备注:分数'")
	private Integer score;
	
	@JsonIgnore
	@JoinColumn(name="judges_id",unique = true)
	@OneToOne(optional = false, mappedBy = "judges", fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.DELETE}) 
	private Question question;
}
