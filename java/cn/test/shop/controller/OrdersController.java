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

	//我的订单页面显示
	@RequestMapping("/order_findByUid")
	public String order_findByUid(HttpServletRequest request,
			Integer page) throws Exception{
		
		User user=(User) request.getSession().getAttribute("loginSuccess");
		Integer uid=user.getUid();
		PageBean<Orders> oPageBean=ordersService.findByUid(uid,page);
		request.setAttribute("oPageBean", oPageBean);
		return "orderList";
		
	}
	
	//根据订单i查询订单
	@RequestMapping("/order_findByOid")
	public String order_findByOid(Integer oid,HttpServletRequest request) throws Exception{
		orders=ordersService.findByOid(oid);
		
		request.setAttribute("orders", orders);
		return "order";
		
	}
	
	@RequestMapping("/order_payOrder")
	public void order_payOrder(HttpServletRequest request,
			String addr,
			String username,
			String phone,
			Integer oid,
			HttpServletResponse response,
			String pd_FrpId) throws Exception{
		Orders order=ordersService.findByOid(oid);
		order.setAddr(addr);
		order.setName(username);
		order.setPhone(phone);
		ordersService.update(order);
		
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.1.109:8080/shop/order_callBack.do"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FpId = pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		response.sendRedirect(sb.toString());
		
	}
	
	@RequestMapping("/order_callBack")
	public String order_callBack(String r6_Order,
			String r3_Amt,
			HttpServletRequest request) throws Exception{
		
		Orders currOrder = ordersService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		ordersService.update(currOrder);
		request.setAttribute("all", "支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
		
	}
}
