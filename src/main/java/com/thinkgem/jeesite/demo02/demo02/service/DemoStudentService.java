/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoStudent;
import com.thinkgem.jeesite.demo02.demo02.dao.DemoStudentDao;

/**
 * 单表生成Service
 * @author xx
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class DemoStudentService extends CrudService<DemoStudentDao, DemoStudent> {

	public DemoStudent get(String id) {
		return super.get(id);
	}
	
	public List<DemoStudent> findList(DemoStudent demoStudent) {
		return super.findList(demoStudent);
	}
	
	public Page<DemoStudent> findPage(Page<DemoStudent> page, DemoStudent demoStudent) {
		return super.findPage(page, demoStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(DemoStudent demoStudent) {
		super.save(demoStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(DemoStudent demoStudent) {
		super.delete(demoStudent);
	}
	
}