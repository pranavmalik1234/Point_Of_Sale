package com.pos.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.CartDao;
import com.pos.models.CartItem;
import com.pos.models.Customer;
import com.pos.springconfigurations.SessionFactoryConfigurations;

@Repository
public class CartDaoImpl implements CartDao{
	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(CartItem.class);

	@Override
	public void addItemtoCart(CartItem cartItem) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();

		try {
			Query q= session.createQuery("from cart where customername=:customername and productid=:productid");
			q.setParameter("customername", cartItem.getCustomername());
			q.setParameter("productid", cartItem.getProductid());
			if(q.list().isEmpty()) {/**Not YET ADDED IN CART*/
				session.save(cartItem);
			}			
		}catch(Exception ex) {}
		closesession(tx,session);	
	}

	@Override
	public List<CartItem> getAllCartItems(CartItem cartItem) {
		List<CartItem>databaselists = null;
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from cart where customername=:customername");
			q.setParameter("customername", cartItem.getCustomername());
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);	
		return databaselists;
	}

	@Override
	public void deleteCartItem(CartItem cartItem) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("delete from cart where customername=:customername and productid=:productid");
			q.setParameter("customername", cartItem.getCustomername());
			q.setParameter("productid", cartItem.getProductid());
			q.executeUpdate();
		}catch(Exception ex) {System.out.println("Cartdaoimpl=>"+ex);}
		closesession(tx,session);	
		
	}

	@Override
	public void deletCart(CartItem cartItem) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("delete from cart where customername=:customername");
			q.setParameter("customername", cartItem.getCustomername());
			q.executeUpdate();
		}catch(Exception ex) {}
		closesession(tx,session);	
		
		
	}
	
	@Override
	public void addSavedOrders(ArrayList<CartItem> cartItemList) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		for(int loop=0;loop<cartItemList.size();loop++) {
			try {
				session.save(cartItemList.get(loop));
			}catch(Exception ex) {}
			
		}
		
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
