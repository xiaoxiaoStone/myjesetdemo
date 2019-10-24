/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoClass;
import com.thinkgem.jeesite.demo02.demo02.dao.DemoClassDao;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoStudent;
import com.thinkgem.jeesite.demo02.demo02.dao.DemoStudentDao;

/**
 * 多表生成Service
 * @author xx
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class DemoClassService extends CrudService<DemoClassDao, DemoClass> {

	@Autowired
	private DemoStudentDao demoStudentDao;
	
	public DemoClass get(String id) {
		DemoClass demoClass = super.get(id);
		demoClass.setDemoStudentList(demoStudentDao.findList(new DemoStudent(demoClass)));
		return demoClass;
	}
	
	public List<DemoClass> findList(DemoClass demoClass) {
		return super.findList(demoClass);
	}
	
	public Page<DemoClass> findPage(Page<DemoClass> page, DemoClass demoClass) {
		return super.findPage(page, demoClass);
	}
	
	@Transactional(readOnly = false)
	public void save(DemoClass demoClass) {
		super.save(demoClass);
		for (DemoStudent demoStudent : demoClass.getDemoStudentList()){
			if (demoStudent.getId() == null){
				continue;
			}
			if (DemoStudent.DEL_FLAG_NORMAL.equals(demoStudent.getDelFlag())){
				if (StringUtils.isBlank(demoStudent.getId())){
					demoStudent.setClassId(demoClass);
					demoStudent.preInsert();
					demoStudentDao.insert(demoStudent);
				}else{
					demoStudent.preUpdate();
					demoStudentDao.update(demoStudent);
				}
			}else{
				demoStudentDao.delete(demoStudent);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DemoClass demoClass) {
		super.delete(demoClass);
		demoStudentDao.delete(new DemoStudent(demoClass));
	}
	
}