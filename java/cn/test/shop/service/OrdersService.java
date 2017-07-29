package cn.test.shop.service;

import cn.test.shop.model.Orders;

public interface OrdersService {

	void save(Orders orders) throws Exception;

	Integer selectLastId() throws Exception;


}
