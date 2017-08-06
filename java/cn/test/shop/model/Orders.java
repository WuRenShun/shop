package cn.test.shop.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Orders {
    private Integer oid;

    private Double total;

    private Date ordertime;

    private Integer state;

    private String name;

    private String phone;

    private String addr;
    
    private Integer uid;

    //一个订单只属于一个用户
    private User user;
    
    //一个订单包含多个订单项
    private List<Orderitem> orderitems =new ArrayList<Orderitem>();

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Orderitem> getOrderitems() {
		return (ArrayList<Orderitem>) orderitems;
	}

	public void setOrderitems(ArrayList<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
    
    

}