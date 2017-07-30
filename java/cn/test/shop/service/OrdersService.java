package cn.test.shop.service;

import cn.test.shop.model.Orders;

public interface OrdersService {

	void save(Orders orders) throws Exception;

	Integer selectLastId() throws Exception;

	PageBean<Orders> findByUid(Integer uid, Integer page) throws Exception;

	Orders findByOid(Integer oid) throws Exception;

	void update(Orders order) throws Exception;


}
