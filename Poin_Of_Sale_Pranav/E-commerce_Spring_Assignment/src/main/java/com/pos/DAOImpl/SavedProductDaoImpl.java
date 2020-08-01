package com.pos.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.SavedProductDao;
import com.pos.models.SaveProducts;
import com.pos.springconfigurations.SessionFactoryConfigurations;

@Repository
public class SavedProductDaoImpl implements SavedProductDao{
	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(SaveProducts.class);

	@Override
	public void addProducts(ArrayList<SaveProducts> savedProductsList) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		for(int loop=0;loop<savedProductsList.size();loop++) {
			SaveProducts savedProduct=savedProductsList.get(loop);
			
			try {
				session.save(savedProduct);
			}catch(Exception ex) {}
			
		}
		
		closesession(tx,session);	
		
	}
	
	
	public List<SaveProducts> getProductsbyId(String orderid){
		Session session= opensession();
		List<SaveProducts>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from SaveProducts where orderid=:orderid");
			q.setParameter("orderid", orderid);
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
	}
	
	@Override
	public void deleteSavedProducts(String orderid) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("delete from SaveProducts where orderid=:orderid");
			q.setParameter("orderid", orderid);
			q.executeUpdate();
		}catch(Exception ex) {}
		closesession(tx,session);	
	}
	
	
	
	public Session opensession() {
		return sessionFactoryConfig.HibernateInitialization(conf).openSession();
	
		
	}
	
	public void closesession(Transaction tx, Session session) {
		tx.commit();
		session.close();
		sessionFactoryConfig.closeSessionFactory();
	}






}
