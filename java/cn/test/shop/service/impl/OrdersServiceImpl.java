package cn.test.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.OrdersMapper;
import cn.test.shop.model.Orders;
import cn.test.shop.service.OrdersService;

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public void save(Orders orders) throws Exception {
		
		ordersMapper.save(orders);
		
	}

	@Override
	public Integer selectLastId() throws Exception {
		// TODO Auto-generated method stub
		return ordersMapper.selectLastId();
	}



	
	
	
}
