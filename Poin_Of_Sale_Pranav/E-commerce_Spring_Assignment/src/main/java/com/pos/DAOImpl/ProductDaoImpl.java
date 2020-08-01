package com.pos.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.ProductDao;
import com.pos.models.Customer;
import com.pos.models.CustomerCart;
import com.pos.models.Product;
import com.pos.springconfigurations.SessionFactoryConfigurations;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(Product.class);

	@Override
	public List<Product> getAllProduct() {
		Session session= opensession();
		List<Product>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from products");
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
	}

	@Override
	public void CreateProduct() {
		System.out.println("product dao");
		Session session= opensession();
		Product p=new Product();
		p.setProductname("Camera");
		p.setProductprice(80);
		p.setProductstock(10);
		p.setProductdescription("Digital Camera");
		Transaction tx=session.beginTransaction();
		try {
			session.save(p);
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
	
	public static void main(String args[]) {
		ProductDaoImpl d= new ProductDaoImpl();
		d.CreateProduct();
	}
	
	public List<Integer> getProducts(Object customerProducts){
		boolean cond=true;
		CustomerCart[] customerCart=(CustomerCart[]) customerProducts;
		Session session= opensession();
		List<Integer>databaselists= new ArrayList<Integer>();
		List<Integer>temp= new ArrayList<Integer>();
		Transaction tx=session.beginTransaction();
		for(int i=0;i<customerCart.length;i++) {
			try {
				Query q= session.createQuery("select productstock from products  where productid=:productid");
				q.setParameter("productid", customerCart[i].getProductid());
				temp=q.list();
				if(customerCart[i].getItemQuantity()>temp.get(0)) {
					break;
				}
				databaselists.addAll(temp);
			}catch(Exception ex) {}
			
		}	
		closesession(tx,session);	
		return databaselists;
	}
	
	public boolean removeBoughtItems(Object customerProducts,ArrayList<Integer> newStock) {
		CustomerCart[] customerCart=(CustomerCart[]) customerProducts;
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		boolean cond=true;
		for(int i=0;i<customerCart.length;i++) {
			try {
				System.out.println(newStock.get(i));
				Query q= session.createQuery("update products set productstock=:productstock" +" where productid=:productid");
				q.setParameter("productstock", newStock.get(i));
//				q.setParameter("productstock", 200);
				q.setParameter("productid", customerCart[i].getProductid());
				int result = q.executeUpdate();
				if(result!=1) {cond=false;}
				
				
			}catch (Exception ex) {	}
			

		}
		closesession(tx,session);	
		return cond;
	}

}
