package com.pos.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.OrderedProductDao;
import com.pos.models.OrderProducts;
import com.pos.models.PlacedOrder;
import com.pos.springconfigurations.SessionFactoryConfigurations;

@Repository
public class OrderedProductDaoImpl implements OrderedProductDao {
	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(OrderProducts.class);

	@Override
	public void addProducts(ArrayList<OrderProducts> orderedProductsList) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		for(int loop=0;loop<orderedProductsList.size();loop++) {
			OrderProducts orderedProduct=orderedProductsList.get(loop);
			
			try {
				session.save(orderedProduct);
			}catch(Exception ex) {}
			
		}
		
		closesession(tx,session);		
		
	}
	
	
	@Override
	public List<OrderProducts> getProductsbyId(String orderid) {
		Session session= opensession();
		List<OrderProducts>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from OrderProducts where orderid=:orderid");
			q.setParameter("orderid", orderid);
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
		
		
	}
	
	@Override
	public List<OrderProducts> getAllOrderedProducts() {
		Session session= opensession();
		List<OrderProducts>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from OrderProducts");
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
		
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
