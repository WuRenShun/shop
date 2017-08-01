package cn.test.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Category;
import cn.test.shop.service.CategoryService;
import cn.test.shop.service.CategorysecondService;

@Controller
public class AdminCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategorysecondService categorysecondService;
	
	//查询一级分类的内容
	@RequestMapping("/adminCategory_findAll")
	public String adminCategory_findAll(HttpServletRequest request) throws Exception{
		
		List<Category> categories=categoryService.findAll();
		request.setAttribute("adminCategory", categories);
		return "/admin/category/list";
		
	}
	
	//添加一级分类的跳转
	@RequestMapping("/categoryAdd")
	public String categoryAdd(){
		
		return "/admin/category/add";
		
	}
	//添加一级分类
	@RequestMapping("/adminCategory_save")
	public String adminCategory_save(Category category) throws Exception{
		
		categoryService.add(category);
		return "redirect:adminCategory_findAll.do";
		
		
	}
	
	//编辑一级分类
	@RequestMapping("/adminCategory_edit")
	public String adminCategory_edit(Integer cid,
			HttpServletRequest request) throws Exception{
		Category category=categoryService.findByCid(cid);
		request.setAttribute("adminCategory", category);
		return "/admin/category/edit";
		
	}
	
	//编辑后的表单提交
	@RequestMapping("/adminCategory_update")
	public String adminCategory_update(Category category) throws Exception{
		
		categoryService.update(category);
		return "redirect:adminCategory_findAll.do";
	}
	
	//删除一级分类
	@RequestMapping("/adminCategory_delete")
	public String adminCategory_delete(Integer cid) throws Exception{
		//删除一级分类前删除二级分类
		categorysecondService.deleteCid(cid);
		categoryService.delete(cid);
		return "redirect:adminCategory_findAll.do";
		
	}

}
