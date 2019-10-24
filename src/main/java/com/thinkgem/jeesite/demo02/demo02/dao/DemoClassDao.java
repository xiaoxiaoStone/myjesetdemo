/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoClass;

/**
 * 多表生成DAO接口
 * @author xx
 * @version 2019-10-23
 */
@MyBatisDao
public interface DemoClassDao extends CrudDao<DemoClass> {
	
}