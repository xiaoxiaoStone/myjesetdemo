/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoClass;
import com.thinkgem.jeesite.demo02.demo02.service.DemoClassService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.demo02.demo02.entity.DemoStudent;
import com.thinkgem.jeesite.demo02.demo02.service.DemoStudentService;

import java.util.List;
import java.util.Map;

/**
 * 单表生成Controller
 * @author xx
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/demo02/demoStudent")
public class DemoStudentController extends BaseController {

	@Autowired
	private DemoStudentService demoStudentService;
	
	@ModelAttribute
	public DemoStudent get(@RequestParam(required=false) String id) {
		DemoStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoStudentService.get(id);
		}
		if (entity == null){
			entity = new DemoStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("demo02:demoStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoStudent demoStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DemoStudent> page = demoStudentService.findPage(new Page<DemoStudent>(request, response), demoStudent); 
		model.addAttribute("page", page);
		return "demo02/demo02/demoStudentList";
	}

	@RequiresPermissions("demo02:demoStudent:view")
	@RequestMapping(value = "form")
	public String form(DemoStudent demoStudent, Model model) {
		model.addAttribute("demoStudent", demoStudent);
		return "demo02/demo02/demoStudentForm";
	}

	@RequiresPermissions("demo02:demoStudent:edit")
	@RequestMapping(value = "save")
	public String save(DemoStudent demoStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demoStudent)){
			return form(demoStudent, model);
		}
		demoStudentService.save(demoStudent);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/demo02/demoStudent/?repage";
	}
	
	@RequiresPermissions("demo02:demoStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(DemoStudent demoStudent, RedirectAttributes redirectAttributes) {
		demoStudentService.delete(demoStudent);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/demo02/demoStudent/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String name, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();

		DemoStudent demoStudent=new DemoStudent();
		demoStudent.setName(name);

		List<DemoStudent> list = demoStudentService.findList(demoStudent);
		for (int i=0; i<list.size(); i++){

			demoStudent=list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id",demoStudent.getId());
			//map.put("pId", officeId);
			map.put("name", demoStudent.getName());
			mapList.add(map);
		}
		return mapList;
	}



}