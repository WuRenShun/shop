package cn.test.shop.service;

import java.util.List;

import cn.test.shop.model.Product;
import cn.test.shop.utils.PageBean;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product findByPid(Integer pid) throws Exception;

	PageBean<Product> findByPageCid(Integer cid, Integer page) throws Exception;

	PageBean<Product> findByPageCsid(Integer csid, Integer page) throws Exception;

}
