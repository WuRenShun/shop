package cn.test.shop.mapping;

import java.util.List;

import cn.test.shop.model.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> findHot();
    
    List<Product> findNew();

    //查询总记录数
	int findCountCid(Integer cid);

	//查询商品
	List<Product> findByPageCid(Integer cid, int begin, int limit);

	
	int findCountCsid(Integer csid);

	List<Product> findByPageCsid(Integer csid, int begin, int limit);

    
   
}