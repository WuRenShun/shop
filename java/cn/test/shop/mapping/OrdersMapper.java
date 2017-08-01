package cn.test.shop.mapping;

import java.util.List;

import cn.test.shop.model.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    void save(Orders orders);

    //查找oid
	int selectLastId();

	//查询该用户的订单总数
	int findCountByUid(Integer uid);

	//查询该用户的订单数据
	List<Orders> findPageByUid(Integer uid, int begin, int limit);

	Orders findByOid(Integer oid);
}