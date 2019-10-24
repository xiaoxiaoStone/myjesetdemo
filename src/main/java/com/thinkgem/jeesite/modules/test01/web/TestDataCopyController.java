/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test01.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test01.entity.TestDataCopy;
import com.thinkgem.jeesite.modules.test01.service.TestDataCopyService;

/**
 * 单表生成Controller
 * @author xx
 * @version 2019-10-22
 */
@Controller
@RequestMapping(value = "${adminPath}/test01/testDataCopy")
public class TestDataCopyController extends BaseController {

	@Autowired
	private TestDataCopyService testDataCopyService;
	
	@ModelAttribute
	public TestDataCopy get(@RequestParam(required=false) String id) {
		TestDataCopy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataCopyService.get(id);
		}
		if (entity == null){
			entity = new TestDataCopy();
		}
		return entity;
	}
	
	@RequiresPermissions("test01:testDataCopy:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestDataCopy testDataCopy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestDataCopy> page = testDataCopyService.findPage(new Page<TestDataCopy>(request, response), testDataCopy); 
		model.addAttribute("page", page);
		return "modules/test01/testDataCopyList";
	}

	@RequiresPermissions("test01:testDataCopy:view")
	@RequestMapping(value = "form")
	public String form(TestDataCopy testDataCopy, Model model) {
		model.addAttribute("testDataCopy", testDataCopy);
		return "modules/test01/testDataCopyForm";
	}

	@RequiresPermissions("test01:testDataCopy:edit")
	@RequestMapping(value = "save")
	public String save(TestDataCopy testDataCopy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testDataCopy)){
			return form(testDataCopy, model);
		}
		testDataCopyService.save(testDataCopy);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/test01/testDataCopy/?repage";
	}
	
	@RequiresPermissions("test01:testDataCopy:edit")
	@RequestMapping(value = "delete")
	public String delete(TestDataCopy testDataCopy, RedirectAttributes redirectAttributes) {
		testDataCopyService.delete(testDataCopy);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/test01/testDataCopy/?repage";
	}

}