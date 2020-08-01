package com.pos.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.pos.models.TotalSalesReport;

public interface ReportService {
	
	public ArrayList<TotalSalesReport> totalsSalesReport( String duration) ;
	public File paymentMethod(String abspath, String duration) throws IOException ;

}
