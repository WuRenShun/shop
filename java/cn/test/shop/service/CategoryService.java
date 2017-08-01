package cn.test.shop.service;

import java.util.List;

import cn.test.shop.model.Category;

public interface CategoryService {

	List<Category> findAll() throws Exception;

	List<Category> findCategoryVos() throws Exception;

	Category findByCid(Integer cid) throws Exception;

	void add(Category category) throws Exception;

	void delete(Integer cid) throws Exception;

	void update(Category category) throws Exception;

}
