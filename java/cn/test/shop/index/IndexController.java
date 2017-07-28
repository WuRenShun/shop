package cn.test.shop.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Category;
import cn.test.shop.model.Product;
import cn.test.shop.service.CategoryService;
import cn.test.shop.service.ProductService;


/**
 * 首页
 * @author RENSHUN
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	//跳转至首页
	@RequestMapping("/index")
	public String index(HttpServletRequest request) throws Exception{
		
		//查询全部一级分类缓存
		List<Category> categories=categoryService.findAll();
		request.getSession().setAttribute("cList", categories);
		
		List<Product> products=productService.findHot();
		request.setAttribute("hList", products);
		
		List<Product> pList=productService.findNew();
		request.setAttribute("nList", pList);
		
		
		return "index";
		
	}
	

}
