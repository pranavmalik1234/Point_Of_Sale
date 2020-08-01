package com.pos.PointofSale;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.pos.constants.Constants;
import com.pos.models.TotalSalesReport;
import com.pos.servicesImpl.ReportServiceImpl;
import com.pos.utility.WriteDatatoExcel;

@Path("/report")
@Component
public class GenerateReports {	
	
	@Context
    private HttpServletRequest servletRequest; 
	@Context
	private HttpServletResponse response;
    
    @Context 
    private ServletContext servletContext;
	
	@Path("/totalsale")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces("application/vnd.ms-excel")
	public Response totalSales(@Context HttpServletRequest servletRequest,final String json) throws Exception {
		System.out.println(json);
//		 System.out.println(servletRequest.getServletContext().getRealPath("/image"));
		 String abspath=servletRequest.getServletContext().getRealPath("/image")+"/";
		
		ArrayList<TotalSalesReport>salesReportList=Constants.context.getBean(ReportServiceImpl.class).totalsSalesReport(json);
		File totalSalesFile=new WriteDatatoExcel().writeToExcel(salesReportList,abspath);
		long r = 0;
	    InputStream is=null;

	    try {
	        is =new FileInputStream(totalSalesFile);
	        r = totalSalesFile.length();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		ResponseBuilder response = Response.ok((Object) totalSalesFile);
		response.header("Content-Disposition","attachment;totalSales_data.xlsx");
		return response.build();
	}
	
	@Path("/paymentmethod")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response paymentMethod(final String json) throws BeansException, IOException {
		String abspath=servletRequest.getServletContext().getRealPath("/image")+"/";
		File paymentsFile=Constants.context.getBean(ReportServiceImpl.class).paymentMethod(abspath,json);
		long r = 0;
	    InputStream is=null;

	    try {
	        is =new FileInputStream(paymentsFile);
	        r = paymentsFile.length();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		ResponseBuilder response = Response.ok((Object) paymentsFile);
		response.header("Content-Disposition","attachment;totalSales_data.xlsx");
		return response.build();

	}
	

}
