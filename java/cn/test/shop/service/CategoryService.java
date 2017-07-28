package cn.test.shop.service;

import java.util.List;

import cn.test.shop.model.Category;

public interface CategoryService {

	List<Category> findAll() throws Exception;

	List<Category> findCategoryVos() throws Exception;

}
