package com.lwj.springcloud.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Entity
@Table(name = "studentExaminfotb")
@GenericGenerator(name = "id", strategy = "increment")
@Data
//学生考试信息表
public class StudentExamInfo {
	@Id
	@GeneratedValue	
	@Column(columnDefinition="int unsigned NOT NULL comment '备注:自动增长主键'")
	private Integer id;
	@Column(columnDefinition="int comment '备注:学生id'")
	private Integer studentId;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="examinfoId",unique = true)
	private Examinfo examinfo;
	@Column(columnDefinition="int comment '备注:分数'")
	private Integer examScore;
	@Column(columnDefinition="int comment '备注:倒计时'")
	private Integer time;
}