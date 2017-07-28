package cn.test.shop.mapping;

import java.util.List;

import cn.test.shop.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> findAll();
    
    List<Category> findCategoryVos();
}