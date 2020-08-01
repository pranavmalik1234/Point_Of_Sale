package com.pos.DAOImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.PlaceOrderDao;
import com.pos.models.CheckOutDetails;
import com.pos.models.Customer;
import com.pos.models.CustomerCart;
import com.pos.models.PlacedOrder;
import com.pos.springconfigurations.SessionFactoryConfigurations;

@Repository
public class PlaceOrderDaoImpl implements PlaceOrderDao{
	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	@Autowired
	ProductDaoImpl productDaoImpl;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(PlacedOrder.class);

	@Override
	public boolean placeOrder(ArrayList<Object> orderDetails) {
		boolean orderStatus=true;
		List<Integer>availableSTock=productDaoImpl.getProducts(orderDetails.get(1));
		CustomerCart[] customerCart=(CustomerCart[]) orderDetails.get(1);
		if(availableSTock.size()==customerCart.length) {
			ArrayList<Integer>newStock=new ArrayList<Integer>();
			Iterator<Integer> iter = availableSTock.iterator(); 
			int loop=0;
			 while (iter.hasNext()) { 
				 int current=iter.next();
				 orderStatus=newStock.add(current-customerCart[loop++].getItemQuantity());
		        } 
			productDaoImpl.removeBoughtItems(orderDetails.get(1),newStock);
		}else {
			orderStatus=false;
		}
		return orderStatus;
	}
	
	public void addOrder(PlacedOrder newOrder, String neworderid) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			session.save(newOrder);
		}catch(Exception ex) {}
		closesession(tx,session);		
	}
	
	
	
	@Override
	public List<PlacedOrder> getOrderDetail(String employeeName) {
		Session session= opensession();
		List<PlacedOrder>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from placedorders where employee=:employee");
			q.setParameter("employee", employeeName);
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
		
	}
	
	@Override
	public List<PlacedOrder> getAllOrders() {
		Session session= opensession();
		List<PlacedOrder>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from placedorders");
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
	}
	
	@Override
	public List<PlacedOrder> getAllOrdersReport(String stDate,String edDate) {
		Session session= opensession();
		List<PlacedOrder>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from placedorders WHERE dateoforder >:stDate and  dateoforder <:edDate");
			q.setParameter("stDate", stDate);
			q.setParameter("edDate", edDate);
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
