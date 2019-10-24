/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 多表生成Entity
 * @author xx
 * @version 2019-10-23
 */
public class DemoClass extends DataEntity<DemoClass> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 班级名
	private List<DemoStudent> demoStudentList = Lists.newArrayList();		// 子表列表
	
	public DemoClass() {
		super();
	}

	public DemoClass(String id){
		super(id);
	}

	@Length(min=0, max=64, message="班级名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<DemoStudent> getDemoStudentList() {
		return demoStudentList;
	}

	public void setDemoStudentList(List<DemoStudent> demoStudentList) {
		this.demoStudentList = demoStudentList;
	}
}