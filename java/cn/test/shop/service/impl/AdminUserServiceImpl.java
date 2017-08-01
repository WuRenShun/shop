package cn.test.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.AdminuserMapper;
import cn.test.shop.model.Adminuser;
import cn.test.shop.service.AdminUserService;

@Service("AdminUserService")
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminuserMapper adminuserMapper;

	//登陆验证
	@Override
	public Adminuser login(Adminuser adminuser) throws Exception {
		return adminuserMapper.login(adminuser);
	}

}
