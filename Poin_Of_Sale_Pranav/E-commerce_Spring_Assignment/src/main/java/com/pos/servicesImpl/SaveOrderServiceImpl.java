package com.pos.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.SaveOrderDao;
import com.pos.models.CheckOutDetails;
import com.pos.models.SavedOrder;
import com.pos.services.SaveOrderService;
import com.pos.utility.DateTimeParser;
import com.pos.utility.OrderIdGenerator;

@Service
public class SaveOrderServiceImpl implements SaveOrderService{
	
	@Autowired
	SaveOrderDao saveOrderDao;
	
	@Autowired
	SavedOrder newOrder;
	@Autowired
	DateTimeParser dateTimeParser;
	@Autowired
	OrderIdGenerator orderid;
	

	@Override
	public String saveOrder(ArrayList<Object> orderDetails) {
		String saveOrderResponse="";
		CheckOutDetails temp= (CheckOutDetails) orderDetails.get(0);
		String emplname=temp.getEmployee();
		String neworderid="#1"+orderid.getOrderId()+emplname;
		saveOrderDao.addOrder(setOrder(orderDetails, neworderid),neworderid);
		return neworderid;
	}

	@Override
	public SavedOrder setOrder(ArrayList<Object> orderDetails, String neworderid) {
		CheckOutDetails checkoutDetails= (CheckOutDetails) orderDetails.get(0);
		newOrder.setOrderid(neworderid);
		newOrder.setEmployee(checkoutDetails.getEmployee());
		newOrder.setCustomer(checkoutDetails.getCustomer());
		newOrder.setTotalprice(checkoutDetails.getSubTotal());
		newOrder.setOrderStatues("onhold");
		newOrder.setDateoforder(dateTimeParser.getDate());
//		newOrder.setDateoforder("2019-08-09");
		newOrder.setTimeoforder(dateTimeParser.getTime());
		newOrder.setPaymentmode(checkoutDetails.getPaymentMode());		
		return newOrder;
	}

	@Override
	public List<SavedOrder> getOrdersEmployee(String employeeName) {
		List<SavedOrder> orderList=saveOrderDao.getOrderDetail(employeeName);
		return orderList;
	}

	@Override
	public void deleteSavedOrder(String orderid) {
		saveOrderDao.deleteSavedOrder(orderid);
		
	}


}
