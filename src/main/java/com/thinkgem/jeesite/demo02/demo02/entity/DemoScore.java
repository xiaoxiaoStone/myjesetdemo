/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author xx
 * @version 2019-10-23
 */
public class DemoScore extends DataEntity<DemoScore> {
	
	private static final long serialVersionUID = 1L;
	private String studentId;		// 学生编号
	private String score;		// 学生成绩
	
	public DemoScore() {
		super();
	}

	public DemoScore(String id){
		super(id);
	}

	@Length(min=0, max=64, message="学生编号长度必须介于 0 和 64 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Length(min=0, max=64, message="学生成绩长度必须介于 0 和 64 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
}