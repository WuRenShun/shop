package cn.test.shop.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.controller.validation.ValidGroup1;
import cn.test.shop.model.User;
import cn.test.shop.service.UserService;

/**
 * User 相关，进行登陆、注册操作
 * @author RENSHUN
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//跳转至注册页面
	@RequestMapping("/user_registPage")
	public String User_registPage() throws Exception{
		
		return "user_register";
		
	}
	
	//跳转至登陆界面
	@RequestMapping("user_loginPage")
	public String user_loginPage() throws Exception{
		return "user_loginPage";
	}
	/**
	 * 检验用户名是否被注册
	 * @param user
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/user_findByName")
	public void findByName(User user,HttpServletResponse response) throws Exception{
		
		if(user.getUsername()==null||user.getUsername()==""){
			response.getWriter().print("<font color='red'>用户名不能为空</font>");
		}else{
			User u=userService.findByUsername(user.getUsername());
			response.setContentType("text/html;charset=UTF-8");
			
			if(u!=null){
				response.getWriter().print("<font color='red'>用户名已经存在</font>");
			}else{
				response.getWriter().print("<font color='green'>用户名可以使用</font>");
			}
		}
		
	}
	
	
	
	/**
	 * 校验与注册
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @param rePassword
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user_register")
	public String register(@Validated(value={ValidGroup1.class}) User user,BindingResult bindingResult,
			Model model,String rePassword,
			HttpServletRequest request,
			String checkcode) throws Exception{
		//校验
		if(bindingResult.hasErrors()){
			
			model.addAttribute("user",user);
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			return "user_register";
		}
		
		//注册
		String checkcode1=(String) request.getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			request.setAttribute("checkcodeError", "验证码输入错误！");
			return "user_register";
		}
		userService.save(user);
		request.setAttribute("all", "注册成功!请去邮箱激活!");
		return "msg";
	}
	
	//用户激活操作
	@RequestMapping("/user_active")
	public String user_active(String code,HttpServletRequest request) throws Exception{
		User existUser=userService.findByCode(code);
		
		if(existUser==null){
			request.setAttribute("all", "激活失败，激活码错误!");
		}else{
			existUser.setState(1);
			existUser.setCode("");
			userService.update(existUser);
			request.setAttribute("all", "激活成功，请前去登陆!");
		}
		return "msg";
		
	}
	
	//登陆
	@RequestMapping("user_login")
	public String user_login(User user,
			Model model,HttpServletRequest request) throws Exception{
		
		model.addAttribute("userLogin", user);
		if(user.getUsername()==null||user.getUsername()==""){
			request.setAttribute("errorLogin", "用户名不能为空！");
			return "user_loginPage";
		}else if(user.getPassword()==null||user.getPassword()==""){
			request.setAttribute("errorLogin", "密码不能为空！");
			return "user_loginPage";
		}
		
		
		User loginUser=userService.login(user);
		if(loginUser==null){
			model.addAttribute("userLogin", user);
			request.setAttribute("errorLogin", "登录失败:用户名或密码错误或用户未激活!");
			return "user_loginPage";
		}else{
			request.getSession().setAttribute("loginSuccess", loginUser);
		}
		return "redirect:index.do";
		
		
	}
	
	//退出登录
	@RequestMapping("quit")
	public String quit(HttpServletRequest request) throws Exception{
		
		request.getSession().invalidate();
		return "redirect:index.do";
		
	}
	
	

}
