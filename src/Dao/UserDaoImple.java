package Dao;

import org.hibernate.Session;

import entity.User;

public class UserDaoImple implements UserDao {

	@Override
	public boolean User_check(String username) {
		Session session = HibernateUtil.getCurrentSession();
		User user = (User)session.get(User.class, username);
		HibernateUtil.closeSession(session);
		if (user!=null) {
			return true;
		}
		else 
			return false;
		
	}

	@Override
	public void User_Login(User user) {
		Session session = HibernateUtil.getCurrentSession();
		session.save(user);
		session.beginTransaction().commit();
		HibernateUtil.closeSession(session);
	}

}
