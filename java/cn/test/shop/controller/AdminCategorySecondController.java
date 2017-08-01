package cn.test.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Category;
import cn.test.shop.model.Categorysecond;
import cn.test.shop.service.CategoryService;
import cn.test.shop.service.CategorysecondService;
import cn.test.shop.utils.PageBean;


@Controller
public class AdminCategorySecondController {
	

	@Autowired
	private CategorysecondService categorysecondService;
	
	@Autowired
	private CategoryService categoryService;
	
	//查找二级分类
	@RequestMapping("/adminCategorySecond_findAll")
	public String adminCategorySecond_findAll(Integer page,
			HttpServletRequest request) throws Exception{
		
		PageBean<Categorysecond> pageBean=categorysecondService.findByPage(page);
		request.setAttribute("CategorysecondPageBean", pageBean);
		return "/admin/categorysecond/list";
		
	}
	
	//跳转至二级分类添加
	@RequestMapping("/adminCategorySecond_addPage")
	public String adminCategorySecond_addPage(HttpServletRequest request) throws Exception{
		
		List<Category> list=categoryService.findAll();
		request.setAttribute("Categorylist", list);
		return "/admin/categorysecond/add";
		
	}
	
	//添加二级分类
	@RequestMapping("/adminCategorySecond_save")
	public String adminCategorySecond_save(Categorysecond categorysecond) throws Exception{
		
		categorysecondService.save(categorysecond);
		return "redirect:adminCategorySecond_findAll.do?page=1";
		
	}
	
	//编辑二级分类数据
	@RequestMapping("/adminCategorySecond_edit")
	public String adminCategorySecond_edit(Integer csid,
			HttpServletRequest request) throws Exception{
		
		Categorysecond categorysecond=categorysecondService.findByCsid(csid);
		
		request.setAttribute("categorysecondFindCsid", categorysecond);
		
		List<Category> list=categoryService.findAll();
		request.setAttribute("clist", list);
		
		return "/admin/categorysecond/edit";
		
		
	}
	
	//提交修改过的二级分类内容
	@RequestMapping("/adminCategorySecond_update")
	public String adminCategorySecond_update(Categorysecond categorysecond) throws Exception{
		
		categorysecondService.update(categorysecond);
		return "redirect:adminCategorySecond_findAll.do?page=1";
	}
	
	@RequestMapping("/adminCategorySecond_delete")
	public String adminCategorySecond_delete(Integer csid) throws Exception{
		
		
		categorysecondService.delete(csid);
		return "redirect:adminCategorySecond_findAll.do?page=1";
	}

	
	

}
