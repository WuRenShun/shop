package cn.test.shop.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 购物车
 * @author RENSHUN
 *
 */
public class Cart implements Serializable{
	
	private Map<Integer,CartItem> map =new LinkedHashMap<Integer, CartItem>();
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//总计
	private double total;
	
	public double getTotal() {
		return total;
	}

	public void addCart(CartItem cartItem){
		Integer i=cartItem.getProduct().getPid();
		if(map.containsKey(i)){
			//存在此商品
			CartItem cItem=map.get(i);
			cItem.setCount(cartItem.getCount()+cItem.getCount());
		}else{
			map.put(i, cartItem);
		}
		total+=cartItem.getSubtotal();
		
	}
	
	public void removeCart(Integer pid){
		CartItem cartItem=map.remove(pid);
		
		total-=cartItem.getSubtotal();
		
	}
	
	public void clearCart(){
		map.clear();
		
		total=0;
	}
	
	

}
