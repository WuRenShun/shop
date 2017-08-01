package cn.test.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import cn.test.shop.model.Categorysecond;
import cn.test.shop.model.Product;
import cn.test.shop.service.CategorysecondService;
import cn.test.shop.service.ProductService;
import cn.test.shop.utils.PageBean;

@Controller
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategorysecondService categorysecondService;

	@RequestMapping("/adminProduct_findAll")
	public String adminProduct_findAll(Integer page,
			HttpServletRequest request) throws Exception{
		
		PageBean<Product> pageBean=productService.findByPage(page);
		request.setAttribute("productPageBean", pageBean);
		return "/admin/product/list";
		
	}
	//跳转至添加数据页面
	@RequestMapping("/adminProduct_addPage")
	public String adminProduct_addPage(HttpServletRequest request) throws Exception{
		//查询二级分类，不带分页
		List<Categorysecond> cslist=categorysecondService.findAll();
		request.setAttribute("csList", cslist);
		return "/admin/product/add";
		
	}
	
	//保存商品的信息
	@RequestMapping("/adminProduct_save")
	public String adminProduct_save(Product product,
			MultipartFile upload,
			HttpServletRequest request) throws Exception{
		
		product.setPdate(new Date());
		//原始名称
		String originalFilename = upload.getOriginalFilename();
		if(upload!=null && originalFilename!=null && originalFilename.length()>0){
			
			String path="D:\\project\\myeclipse\\shop\\shop\\src\\main\\webapp\\products\\1\\";
			
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(path+newFileName);
			
			//将内存中的数据写入磁盘
			upload.transferTo(newFile);
			
			product.setImage("products/1/" + newFileName);
		}
		productService.save(product);
		
		return "redirect:adminProduct_findAll.do?page=1";
		
	}
	
	//跳转至修改页面
	@RequestMapping("/adminProduct_edit")
	public String adminProduct_edit(Integer pid,
			HttpServletRequest request) throws Exception{
		
		Product product=productService.findByPid(pid);
		request.setAttribute("products", product);
		
		List<Categorysecond> cslist=categorysecondService.findAll();
		request.setAttribute("csList", cslist);
		
		return "/admin/product/edit";
		
	}
	
	//保存修改后的商品信息
	@RequestMapping("/adminProduct_update")
	public String adminProduct_update(Product product,
			MultipartFile upload,
			HttpServletRequest request) throws Exception{
		
		product.setPdate(new Date());
		//原始名称
		String originalFilename = upload.getOriginalFilename();
		if(upload!=null && originalFilename!=null && originalFilename.length()>0){
			
			String path="D:\\project\\myeclipse\\shop\\shop\\src\\main\\webapp\\products\\1\\";
			String delPath="D:\\project\\myeclipse\\shop\\shop\\src\\main\\webapp\\"+product.getImage();
			File file = new File(delPath);
			file.delete();
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(path+newFileName);
			
			//将内存中的数据写入磁盘
			upload.transferTo(newFile);
			
			product.setImage("products/1/" + newFileName);
		}
		productService.update(product);
		
		return "redirect:adminProduct_findAll.do?page=1";
		
	}
	
	@RequestMapping("/adminProduct_delete")
	public String adminProduct_delete(Integer pid) throws Exception{
		
		productService.delete(pid);
		return "redirect:adminProduct_findAll.do?page=1";
		
	}
	
	
}
