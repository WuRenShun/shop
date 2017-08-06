package cn.test.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Orderitem;
import cn.test.shop.model.Orders;
import cn.test.shop.service.OrdersService;
import cn.test.shop.utils.PageBean;

/**
 * 后台订单管理
 * @author RENSHUN
 *
 */
@Controller
public class AdminOrderController {
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/adminOrder_findAll")
	public String adminOrder_findAll(Integer page,
			HttpServletRequest request) throws Exception{
		
		PageBean<Orders> pageBean=ordersService.findAll(page);
		request.setAttribute("pageBean", pageBean);
		return "/admin/orders/list";
		
	}
	//显示详情
	@RequestMapping("/adminOrder_findOrderItem")
	public String adminOrder_findOrderItem(Integer oid,
			HttpServletRequest request) throws Exception{
		List<Orderitem> list = ordersService.findOrderItem(oid);
		
		request.setAttribute("list", list);
		return "/admin/orders/orderItem";
		
	}
	
	
	//发货
	@RequestMapping("/adminOrder_updateState")
	public String adminOrder_updateState(Integer oid) throws Exception{
		
		Orders orders=ordersService.findByOid(oid);
		orders.setState(3);
		ordersService.update(orders);
		return "redirect:adminOrder_findAll.do?page=1";
		
	}
	

}
