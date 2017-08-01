package cn.test.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.CategoryMapper;
import cn.test.shop.model.Category;
import cn.test.shop.service.CategoryService;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoryMapper.findAll();
	}

	@Override
	public List<Category> findCategoryVos() throws Exception {
		return categoryMapper.findCategoryVos();
	}

	@Override
	public Category findByCid(Integer cid) throws Exception {
		return categoryMapper.selectByPrimaryKey(cid);
	}

	@Override
	public void add(Category category) throws Exception {
		categoryMapper.insertSelective(category);
	}

	@Override
	public void delete(Integer cid) throws Exception {
		categoryMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public void update(Category category) throws Exception {
		categoryMapper.updateByPrimaryKeySelective(category);
	}
	
	
	
	

}
