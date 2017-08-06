package cn.test.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.OrdersMapper;
import cn.test.shop.model.Orderitem;
import cn.test.shop.model.Orders;
import cn.test.shop.service.OrdersService;
import cn.test.shop.utils.PageBean;

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
	
	// 业务层根据用户id查询订单,带分页查询.
	@Override
	public PageBean<Orders> findByUid(Integer uid, Integer page) throws Exception {
			
		PageBean<Orders> oBean=new PageBean<Orders>();
		
		oBean.setPage(page);
		
		int limit=5;
		oBean.setLimit(limit);
		//总记录数
		int totalCount=0;
		totalCount=ordersMapper.findCountByUid(uid);
		oBean.setTotalCount(totalCount);
		//总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit +1;
		}
		oBean.setTotalPage(totalPage);
			
		int begin=(page - 1)*limit;
		List<Orders> list = ordersMapper.findPageByUid(uid,begin,limit);
		oBean.setList(list);
		return oBean;
			
	}

	@Override
	public Orders findByOid(Integer oid) throws Exception {
		return ordersMapper.findByOid(oid);
	}

	@Override
	public void update(Orders order) throws Exception {
		ordersMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public PageBean<Orders> findAll(Integer page) throws Exception {
		PageBean<Orders> oBean=new PageBean<Orders>();
		
		oBean.setPage(page);
		
		int limit=10;
		oBean.setLimit(limit);
		//总记录数
		int totalCount=0;
		totalCount=ordersMapper.findCount();
		oBean.setTotalCount(totalCount);
		//总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit +1;
		}
		oBean.setTotalPage(totalPage);
			
		int begin=(page - 1)*limit;
		List<Orders> list = ordersMapper.findPage(begin,limit);
		oBean.setList(list);
		return oBean;
	}

	@Override
	public List<Orderitem> findOrderItem(Integer oid) throws Exception {
		return ordersMapper.findOrderItem(oid);
	}



	
	
	
}
