package cn.test.shop.service;

import cn.test.shop.model.User;

public interface UserService {

	public User findByUsername(String username) throws Exception;

	public void save(User user) throws Exception;

	public User findByCode(String code) throws Exception;

	public void update(User existUser) throws Exception;

	public User login(User user) throws Exception;

	
	
}
