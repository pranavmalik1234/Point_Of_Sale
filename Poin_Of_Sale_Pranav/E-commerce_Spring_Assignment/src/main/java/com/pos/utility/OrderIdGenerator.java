package com.pos.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {
	
	@Autowired
	DateTimeParser dateTimeParser;
	
	private String OrderId;

	public String getOrderId() {
		OrderId=dateTimeParser.getUniqueOrderId();
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	
	public static void main(String args[]) {
	}
	

}
