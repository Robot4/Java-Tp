package com.emsi.Maven.JDBC;

import com.emsi.Maven.JDBC.entities.Seller;
import com.emsi.Maven.JDBC.service.SellerService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SellerService  sellerService = new SellerService();
    	for( Seller seller :sellerService.findAll())
    		System.out.println(seller);
    }
}
