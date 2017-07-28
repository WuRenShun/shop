package cn.test.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Category;
import cn.test.shop.model.Product;
import cn.test.shop.service.CategoryService;
import cn.test.shop.service.ProductService;
import cn.test.shop.utils.PageBean;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/{pid}/product_findByPid")
	public String product_findByPid(@PathVariable("pid") Integer pid ,
			HttpServletRequest request) throws Exception{
		List<Category> categoryVos=categoryService.findCategoryVos();
		request.setAttribute("categoryVos", categoryVos);
		Product product=productService.findByPid(pid);
		request.setAttribute("products", product);
		return "product";
		
	}
	
	@RequestMapping("/{cid}/product_findByCid")
	public String product_findByCid(@PathVariable("cid") Integer cid,
			Integer page, 
			HttpServletRequest request) throws Exception{
		//显示productList左边部分
		List<Category> categoryVos=categoryService.findCategoryVos();
		request.setAttribute("categoryVos", categoryVos);
		//显示productList右边部分图片
		request.setAttribute("cid", cid);
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		request.setAttribute("pageBean", pageBean);
		return "productList";
		
	}
	
	@RequestMapping("/{csid}/product_findByCsid")
	public String product_findByCsid(@PathVariable("csid") Integer csid,
			Integer page, 
			HttpServletRequest request) throws Exception{
		List<Category> categoryVos=categoryService.findCategoryVos();
		request.setAttribute("categoryVos", categoryVos);
		
		request.setAttribute("csid", csid);
		PageBean<Product> pageBean=productService.findByPageCsid(csid, page);
		request.setAttribute("pageBean", pageBean);
		return "productList";
		
	}

}
