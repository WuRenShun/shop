package cn.test.shop.mapping;

import cn.test.shop.model.Categorysecond;

public interface CategorysecondMapper {
    int deleteByPrimaryKey(Integer csid);

    int insert(Categorysecond record);

    int insertSelective(Categorysecond record);

    Categorysecond selectByPrimaryKey(Integer csid);

    int updateByPrimaryKeySelective(Categorysecond record);

    int updateByPrimaryKey(Categorysecond record);
}