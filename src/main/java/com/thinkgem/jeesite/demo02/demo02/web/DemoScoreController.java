/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.demo02.demo02.web;

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
import com.thinkgem.jeesite.demo02.demo02.entity.DemoScore;
import com.thinkgem.jeesite.demo02.demo02.service.DemoScoreService;


/**
 * 单表生成Controller
 * @author xx
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/demo02/demoScore")
public class DemoScoreController extends BaseController {
	private  int i;

	@Autowired
	private DemoScoreService demoScoreService;
	
	@ModelAttribute
	public DemoScore get(@RequestParam(required=false) String id) {
		DemoScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoScoreService.get(id);
		}
		if (entity == null){
			entity = new DemoScore();
		}
		return entity;
	}
	
	@RequiresPermissions("demo02:demoScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoScore demoScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DemoScore> page = demoScoreService.findPage(new Page<DemoScore>(request, response), demoScore); 
		model.addAttribute("page", page);
		return "demo02/demo02/demoScoreList";
	}

	@RequiresPermissions("demo02:demoScore:view")
	@RequestMapping(value = "form")
	public String form(DemoScore demoScore, Model model) {
		model.addAttribute("demoScore", demoScore);
		return "demo02/demo02/demoScoreForm";
	}

	@RequiresPermissions("demo02:demoScore:edit")
	@RequestMapping(value = "save")
	public String save(DemoScore demoScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demoScore)){
			return form(demoScore, model);
		}
		demoScoreService.save(demoScore);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/demo02/demoScore/?repage";
	}
	
	@RequiresPermissions("demo02:demoScore:edit")
	@RequestMapping(value = "delete")
	public String delete(DemoScore demoScore, RedirectAttributes redirectAttributes) {
		demoScoreService.delete(demoScore);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/demo02/demoScore/?repage";
	}

}