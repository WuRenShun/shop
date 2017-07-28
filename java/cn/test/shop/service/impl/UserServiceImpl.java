package cn.test.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.UserMapper;
import cn.test.shop.model.User;
import cn.test.shop.service.UserService;
import cn.test.shop.utils.MailUtils;
import cn.test.shop.utils.UUIDUtils;


@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) throws Exception{
		
		User user=userMapper.findByUsername(username);
		
		return user;
	}

	@Override
	public void save(User user) throws Exception {
		user.setState(0);
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userMapper.insertSelective(user);
		MailUtils.sendMail(user.getEmail(), code);
		
	}

	@Override
	public User findByCode(String code) throws Exception {
		
		return userMapper.findByCode(code);
	}

	@Override
	public void update(User existUser) throws Exception {

		userMapper.updateByPrimaryKeySelective(existUser);
	}

	@Override
	public User login(User user) throws Exception {
		user.setState(1);
		return userMapper.findUser(user);
	}

	
	
}
