package cn.test.shop.model;

/**
 * 购物项
 * @author RENSHUN
 *
 */
public class CartItem {
	
	private Product product; //购物车里面的商品信息
	private int count;  //商品数量
	private double subtotal;  //小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//直接计算
	public double getSubtotal() {
		return count*product.getShopPrice();
	}

	
	
	

}
