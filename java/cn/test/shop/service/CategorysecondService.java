package cn.test.shop.service;

import java.util.List;

import cn.test.shop.model.Categorysecond;
import cn.test.shop.utils.PageBean;

public interface CategorysecondService {

	PageBean<Categorysecond> findByPage(Integer page) throws Exception;

	void save(Categorysecond categorysecond) throws Exception;

	Categorysecond findByCsid(Integer csid) throws Exception;

	void update(Categorysecond categorysecond) throws Exception;

	void delete(Integer csid) throws Exception;

	void deleteCid(Integer cid) throws Exception;

	List<Categorysecond> findAll() throws Exception;

	


}
