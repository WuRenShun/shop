package cn.test.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.test.shop.model.Cart;
import cn.test.shop.model.CartItem;
import cn.test.shop.model.Product;
import cn.test.shop.service.ProductService;

@Controller
public class CartController{
	
	@Autowired
	private ProductService productService;
	
	

	//将购物项添加到购物车
	@RequestMapping("/cart_add")
	public String cart_add(Integer pid,
			Integer count,
			HttpServletRequest request) throws Exception{
		
		CartItem cartItem=new CartItem();
		//设置数量
		cartItem.setCount(count);
		//取得商品
		Product product=productService.findByPid(pid);
		cartItem.setProduct(product);
		// 将购物项添加到购物车.
		// 购物车应该存在session中.
		Cart cart=getCart(request);
		cart.addCart(cartItem);
		
		return "cart";
		
	}
	
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request) throws Exception{
		//获得对象
		Cart cart=getCart(request);
		cart.clearCart();
		
		return "cart";
		
	}
	
	@RequestMapping("/myCart")
	public String myCart() throws Exception{
		return "cart";
	}

	@RequestMapping("/deleteCart")
	public String deleteCart(HttpServletRequest request,
			Integer pid) throws Exception{
		Cart cart=getCart(request);
		cart.removeCart(pid);
		return "cart";
		
	}
	private Cart getCart(HttpServletRequest request){
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		return cart;
	}
}
