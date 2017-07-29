package cn.test.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Cart;
import cn.test.shop.model.CartItem;
import cn.test.shop.model.Orderitem;
import cn.test.shop.model.Orders;
import cn.test.shop.model.User;
import cn.test.shop.service.OrderitemService;
import cn.test.shop.service.OrdersService;

@Controller
public class OrdersController {

	private Orders orders = new Orders();
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderitemService orderitemService;
	
	
	
	@RequestMapping("/order_saveOrder")
	public String order_saveOrder(HttpServletRequest request) throws Exception{
		//购物车在session中
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			request.setAttribute("all", "亲!您还没有购物!");
			return "msg";
		}
		orders.setTotal(cart.getTotal());
		//订单的状态，1：未付款
		orders.setState(1);
		orders.setOrdertime(new Date());
		
		//用户
		User user=(User) request.getSession().getAttribute("loginSuccess");
		if(user==null){
			request.setAttribute("errorLogin", "亲!您还没有登录!");
			return "user_loginPage";
		}
		orders.setUser(user);
		orders.setUid(user.getUid());
		
		List ls=new ArrayList();
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			Orderitem orderItem = new Orderitem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrders(orders);

			ls.add(orderItem);
		}
		orders.setOrderitems((ArrayList<Orderitem>) ls);
		//保存订单,关联插入
		ordersService.save(orders);
		//清空购物车
		cart.clearCart();
		for(int i=0;i<orders.getOrderitems().size();i++){
			Orderitem orderItem = orders.getOrderitems().get(i);
			orderItem.setOid(orders.getOid());
			orderitemService.save(orderItem);
		}
		
		
		request.setAttribute("orders", orders);
		
		
		return "order";
		
		
	}
}
