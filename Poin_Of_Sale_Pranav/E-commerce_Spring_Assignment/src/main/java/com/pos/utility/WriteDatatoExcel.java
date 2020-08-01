package com.pos.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pos.models.TotalSalesReport;

import java.sql.Timestamp;

public class WriteDatatoExcel {
   public static File writeToExcel(ArrayList<TotalSalesReport> avail, String abspath) throws Exception {

      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet spreadsheet = workbook.createSheet( "Sales Info ");
      XSSFRow row;

      Map < String, Object[] > info = new TreeMap < String, Object[] >();
      info.put( "1", new Object[] {
    	         "Total Sales Report"});
      info.put( "2", new Object[] {
         "S.No", "EmployeeName", "EmployeeSales", "CustomerName","CustomerSales","Products","ProductsSales" });
      for(int i=1;i<avail.size()+1;i++) {
    	  TotalSalesReport current=avail.get(i-1);
    	  String id = Integer.toString(i+1); 
    	  String employeeName= current.getEmployeeName();
    	  int temp=current.getEmloyeeSales();
    	  String employeeSales = new Integer(temp).toString();
    	  String customerName =current.getCustomereName();
    	  temp=current.getCustomerSales();
    	  String customerSales=new Integer(temp).toString();
    	  String productName=current.getProductName();
    	  temp=current.getProductSales();
    	  String productSales=new Integer(temp).toString();
    	  info.put(id+1, new Object[]{
    			  id,employeeName,employeeSales, customerName,customerSales,productName,productSales
    	  });
      }

      Set < String > keyid = info.keySet();
      int rowid = 0;
      
      for (String key : keyid) {
         row = spreadsheet.createRow(rowid++);
         Object [] objectArr = info.get(key);
         int cellid = 0;
         
         for (Object obj : objectArr){
            Cell cell = row.createCell(cellid++);
            cell.setCellValue((String)obj);
         }
      }
      
      Date date= new Date();
      long time = date.getTime();
     
      FileOutputStream out = new FileOutputStream(
         new File(abspath+"report.xls"));
      
      
      workbook.write(out);
      out.close();
      File totalSalesFile=new File(abspath+"report.xls");
      return totalSalesFile;
   }
   
   public File writePaymentModesReport(int cashValue, int cardValue, int cardOrders, int cashOrders, String abspath) throws IOException {
	   XSSFWorkbook workbook = new XSSFWorkbook();
	      XSSFSheet spreadsheet = workbook.createSheet( " Payment Info ");
	      XSSFRow row;
	      Map < String, Object[] > info = new TreeMap < String, Object[] >();
	      info.put( "1", new Object[] {
	 	         " Payment Method   ","cash"," card "});
	      info.put("2", new Object[]{
    			  "Values ",new Integer(cashValue).toString(),new Integer(cardValue).toString()
    	  });
	      info.put("3", new Object[]{
	    		  "No of Orders ",new Integer(cashOrders).toString(),new Integer(cardOrders).toString() 
    	  });
	      
	      Set < String > keyid = info.keySet();
	      int rowid = 0;
	      
	      for (String key : keyid) {
	         row = spreadsheet.createRow(rowid++);
	         Object [] objectArr = info.get(key);
	         int cellid = 0;
	         
	         for (Object obj : objectArr){
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)obj);
	         }
	      }
	      
	      Date date= new Date();
	      long time = date.getTime();
	      FileOutputStream out = new FileOutputStream(
	    		  new File(abspath+"paymentmethods.xls"));

	      
	      
	      workbook.write(out);
	      out.close();
	      File paymentsFile=new File(abspath+"paymentmethods.xls");
	      return paymentsFile;
	   
   }
   

}
   