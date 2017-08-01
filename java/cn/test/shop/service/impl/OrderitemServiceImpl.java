package cn.test.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.OrderitemMapper;
import cn.test.shop.model.Orderitem;
import cn.test.shop.model.Orders;
import cn.test.shop.service.OrderitemService;
import cn.test.shop.utils.PageBean;

@Service("OrderitemService")
public class OrderitemServiceImpl implements OrderitemService {

	@Autowired
	private OrderitemMapper orderitemMapper;
	
	@Override
	public void save(Orderitem orderItem) throws Exception {

		orderitemMapper.insertSelective(orderItem);
	}

	

}
