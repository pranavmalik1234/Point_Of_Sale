package com.pos.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.sun.corba.se.spi.orbutil.fsm.State;

@Component
public class DateTimeParser {
	static Date now = new Date();
	private int sessionId=100;
	
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId() {
		this.sessionId = this.sessionId+1;
	}
	public String getDate() {
		Date newDate = new Date();
		now=newDate ;
		String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return  formatter.format(now);
		
	}
	public String getTime() {
		Date newDate = new Date();
		now=newDate ;
		return new SimpleDateFormat("HH:mm:ss").format(now);			
	}
	
	public String getUniqueOrderId() {

		String time=new DateTimeParser().getTime().replace(":","");
		String date=new DateTimeParser().getDate().replace("-","");
		String sesnId=Integer.toString(getSessionId());
		setSessionId();
		return date+time+sesnId;
		
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(new DateTimeParser().getTime().replace(":",""));
		System.out.println(new DateTimeParser().getDate().replace("-",""));
       
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//        String mysqlDateString = formatter.format(now);
//        System.out.println("Java's Default Date Format: " + now);
//        System.out.println("Mysql's Default Date Format: " + mysqlDateString);
//        System.out.println("swSpinnerTimeValue: " + new SimpleDateFormat("HH:mm:ss")
//        	    .format(now));
    }
}
