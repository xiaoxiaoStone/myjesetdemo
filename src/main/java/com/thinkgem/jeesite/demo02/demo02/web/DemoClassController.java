/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.thinkgem.jeesite.demo02.demo02.entity.DemoClass;
import com.thinkgem.jeesite.demo02.demo02.service.DemoClassService;

import java.util.List;
import java.util.Map;


/**
 * 多表生成Controller
 * @author xx
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/demo02/demoClass")
public class DemoClassController extends BaseController {

	@Autowired
	private DemoClassService demoClassService;
	
	@ModelAttribute
	public DemoClass get(@RequestParam(required=false) String id) {
		DemoClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoClassService.get(id);
		}
		if (entity == null){
			entity = new DemoClass();
		}
		return entity;
	}
	
	@RequiresPermissions("demo02:demoClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoClass demoClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DemoClass> page = demoClassService.findPage(new Page<DemoClass>(request, response), demoClass); 
		model.addAttribute("page", page);
		return "demo02/demo02/demoClassList";
	}

	@RequiresPermissions("demo02:demoClass:view")
	@RequestMapping(value = "form")
	public String form(DemoClass demoClass, Model model) {
		model.addAttribute("demoClass", demoClass);
		return "demo02/demo02/demoClassForm";
	}

	@RequiresPermissions("demo02:demoClass:edit")
	@RequestMapping(value = "save")
	public String save(DemoClass demoClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demoClass)){
			return form(demoClass, model);
		}
		demoClassService.save(demoClass);
		addMessage(redirectAttributes, "保存多表成功");
		return "redirect:"+Global.getAdminPath()+"/demo02/demoClass/?repage";
	}
	
	@RequiresPermissions("demo02:demoClass:edit")
	@RequestMapping(value = "delete")
	public String delete(DemoClass demoClass, RedirectAttributes redirectAttributes) {
		demoClassService.delete(demoClass);
		addMessage(redirectAttributes, "删除多表成功");
		return "redirect:"+Global.getAdminPath()+"/demo02/demoClass/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String name, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();

		DemoClass demoClass=new DemoClass();
		demoClass.setName(name);



		List<DemoClass> list = demoClassService.findList(demoClass);
		for (int i=0; i<list.size(); i++){

			demoClass=list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", demoClass.getId());
			//map.put("pId", officeId);
			map.put("name", demoClass.getName());
			mapList.add(map);
		}
		return mapList;
	}

}