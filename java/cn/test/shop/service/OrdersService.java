package cn.test.shop.service;

import java.util.List;

import cn.test.shop.model.Orderitem;
import cn.test.shop.model.Orders;
import cn.test.shop.utils.PageBean;

public interface OrdersService {

	void save(Orders orders) throws Exception;

	Integer selectLastId() throws Exception;

	PageBean<Orders> findByUid(Integer uid, Integer page) throws Exception;

	Orders findByOid(Integer oid) throws Exception;

	void update(Orders order) throws Exception;

	PageBean<Orders> findAll(Integer page) throws Exception;

	List<Orderitem> findOrderItem(Integer oid) throws Exception;
}
