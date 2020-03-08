package Dao;

import org.hibernate.Session;

import entity.GreenHouseStat;

public class GreenHouseStatDaoImple implements GreenHouseStatDao {

	@Override
	public void GreenHouseStat_Add(GreenHouseStat ghs) {
		Session session = HibernateUtil.getCurrentSession();
		session.save(ghs);
		session.beginTransaction().commit();
		HibernateUtil.closeSession(session);
	}

}
