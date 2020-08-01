package com.pos.servicesImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.OrderedProductDao;
import com.pos.DAO.PlaceOrderDao;
import com.pos.models.OrderProducts;
import com.pos.models.PlacedOrder;
import com.pos.models.TotalSalesReport;
import com.pos.services.ReportService;
import com.pos.utility.WriteDatatoExcel;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	PlaceOrderDao placeOrderDao;
	@Autowired
	 OrderedProductDao orderedProductDao;
	


	public ArrayList<TotalSalesReport> totalsSalesReport(String duration) {
		
		  Map employeeSales=new HashMap();
		  Map customerSales=new HashMap();
		  Map productSales=new HashMap();
		

			JSONObject jsonObject = new JSONObject( duration);
			String stDate=jsonObject.getString("stDate");
			String edDate=jsonObject.getString("edDate");
		  ArrayList<PlacedOrder>allOrders=(ArrayList<PlacedOrder>) placeOrderDao.getAllOrdersReport(stDate, edDate);
		ArrayList<OrderProducts>allProducts=(ArrayList<OrderProducts>) orderedProductDao.getAllOrderedProducts();
		Iterator<PlacedOrder> iter = allOrders.iterator(); 
		Iterator<OrderProducts> iter1=allProducts.iterator();
		int totalSalesValue=0;
        while (iter.hasNext()) { 
        	PlacedOrder current=iter.next();
            totalSalesValue+=current.getTotalprice();
            if(! employeeSales.containsKey(current.getEmployee())){
                employeeSales.put(current.getEmployee(), current.getTotalprice());
            }else {
            	int x=(int) employeeSales.get(current.getEmployee());
            	x+=current.getTotalprice();
            	employeeSales.put(current.getEmployee(), x ) ;
            }
            if(! customerSales.containsKey(current.getCustomer())){
            	customerSales.put(current.getCustomer(),current.getTotalprice());
            }else {
            	int x=(int) customerSales.get(current.getCustomer());
            	x+=current.getTotalprice();
            	customerSales.put(current.getCustomer(),x);
            }
        } 
        
        
        while (iter1.hasNext()) { 
        	OrderProducts current=iter1.next();
            if(! productSales.containsKey(current.getProductname())){
            	productSales.put(current.getProductname(), current.getProductquantity());
            }else {
            	int y=(int) productSales.get(current.getProductname());
            	int  x=y+current.getProductquantity();
            	productSales.put(current.getProductname(), x);
            }
            
        } 
        Set set=productSales.entrySet();
		Iterator itr=set.iterator();
		
		Iterator<Map.Entry<String, Integer>> employeeIterator =employeeSales.entrySet().iterator(); 
		Iterator<Map.Entry<String, Integer>> customerIterator =customerSales.entrySet().iterator(); 
		Iterator<Map.Entry<String, Integer>> productIterator =productSales.entrySet().iterator(); 
		ArrayList<TotalSalesReport> salesReportList= new ArrayList<TotalSalesReport>();
        
        while( employeeIterator.hasNext() || customerIterator.hasNext() || productIterator.hasNext() ) 
        { 
        	TotalSalesReport reportData= new TotalSalesReport();
        	if(employeeIterator.hasNext()){
        		Map.Entry<String, Integer> entry =  employeeIterator.next(); 
//        		System.out.println("Key = " + entry.getKey() +  
//                        ", Value = " + entry.getValue()); 
        	reportData.setEmployeeName(entry.getKey());
        	reportData.setEmloyeeSales(entry.getValue());
        		
        	}
			if(customerIterator.hasNext()){
        		Map.Entry<String, Integer> entry =  customerIterator.next(); 
        	reportData.setCustomereName(entry.getKey());
        	reportData.setCustomerSales(entry.getValue());
			        		
			}
			if(productIterator.hasNext()) {
        		Map.Entry<String, Integer> entry =  productIterator.next(); 
        	reportData.setProductName(entry.getKey());
        	reportData.setProductSales(entry.getValue());
				
			}
			salesReportList.add(reportData);
             
             
        }
        return salesReportList;
		
		
	}

	public File paymentMethod(String abspath, String duration) throws IOException {
//
		JSONObject jsonObject = new JSONObject( duration);
		String stDate=jsonObject.getString("stDate");
		String edDate=jsonObject.getString("edDate");
		ArrayList<PlacedOrder>allOrders=(ArrayList<PlacedOrder>) placeOrderDao.getAllOrdersReport(stDate, edDate);
		Iterator<PlacedOrder> iter = allOrders.iterator(); 
		int cashValue=0;
		int cardValue=0;
		int cardOrders=0;
		int cashOrders=0;
		while (iter.hasNext()) { 
			PlacedOrder current=iter.next();
			if("cash".equals(current.getPaymentmode())) {
				cashValue+=current.getTotalprice();
				cashOrders++;
			}else {
				cardValue+=current.getTotalprice();
				cardOrders++;
			}
		}
		return new WriteDatatoExcel().writePaymentModesReport(cashValue,cardValue,cardOrders,cashOrders,abspath);
		
	}
	
	

}
