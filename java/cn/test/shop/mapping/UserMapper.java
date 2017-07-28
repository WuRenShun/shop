package cn.test.shop.mapping;

import cn.test.shop.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByUsername(String username);
    
    User findByCode(String code);
    
    User findUser(User user);
    
}