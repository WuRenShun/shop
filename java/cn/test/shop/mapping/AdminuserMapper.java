package cn.test.shop.mapping;

import cn.test.shop.model.Adminuser;

public interface AdminuserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Adminuser record);

    int insertSelective(Adminuser record);

    Adminuser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Adminuser record);

    int updateByPrimaryKey(Adminuser record);
}