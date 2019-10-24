/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 多表生成Entity
 * @author xx
 * @version 2019-10-23
 */
public class DemoStudent extends DataEntity<DemoStudent> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 学生姓名
	private DemoClass classId;		// 班级id 父类
	
	public DemoStudent() {
		super();
	}

	public DemoStudent(String id){
		super(id);
	}

	public DemoStudent(DemoClass classId){
		this.classId = classId;
	}

	@Length(min=0, max=20, message="学生姓名长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="班级id长度必须介于 0 和 64 之间")
	public DemoClass getClassId() {
		return classId;
	}

	public void setClassId(DemoClass classId) {
		this.classId = classId;
	}
	
}