package cn.test.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Adminuser;
import cn.test.shop.service.AdminUserService;

@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping("/backstageIndex")
	public String backstageIndex(){
		
		
		return "/admin/backstageIndex";
		
	}
	
	@RequestMapping("/adminUser_login")
	public String adminUser_login(Adminuser adminuser,
			HttpServletRequest request) throws Exception{
		
		Adminuser adminuser2=adminUserService.login(adminuser);
		
		if(adminuser2==null){
			request.setAttribute("errorAdminLogin", "用户名或密码错误!");
			return "/admin/backstageIndex";
		}
		
		request.getSession().setAttribute("adminLoginUser", adminuser2);
		
		
		return "/admin/home";
		
	}
	
	@RequestMapping("/top")
	public String top(){
		
		return "/admin/top";
	}
	
	@RequestMapping("/bottom")
	public String bottom(){
		
		return "/admin/bottom";
	}
	
	@RequestMapping("/left")
	public String left(){
		
		return "/admin/left";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		
		return "/admin/welcome";
	}

}
