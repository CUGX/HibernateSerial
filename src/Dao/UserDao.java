package Dao;

import entity.User;

public interface UserDao {
	//查询数据库中是否存在某个用户
	public abstract boolean User_check(String username);
	//用户注册
	public abstract void User_Login(User user);
}
