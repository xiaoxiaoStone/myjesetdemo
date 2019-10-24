/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoScore;
import com.thinkgem.jeesite.demo02.demo02.dao.DemoScoreDao;

/**
 * 单表生成Service
 * @author xx
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class DemoScoreService extends CrudService<DemoScoreDao, DemoScore> {

	public DemoScore get(String id) {
		return super.get(id);
	}
	
	public List<DemoScore> findList(DemoScore demoScore) {
		return super.findList(demoScore);
	}
	
	public Page<DemoScore> findPage(Page<DemoScore> page, DemoScore demoScore) {
		return super.findPage(page, demoScore);
	}
	
	@Transactional(readOnly = false)
	public void save(DemoScore demoScore) {
		super.save(demoScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(DemoScore demoScore) {
		super.delete(demoScore);
	}
	
}