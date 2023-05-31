package com.emsi.Maven.JDBC.service;

import java.util.List;

import com.emsi.Maven.JDBC.dao.SellerDao;
import com.emsi.Maven.JDBC.dao.impl.SellerDaoImp;
import com.emsi.Maven.JDBC.entities.Seller;

public class SellerService {
	private SellerDao sellerDao = new SellerDaoImp();

	public List<Seller> findAll() {
		return sellerDao.findAll();
	}

	public void save(Seller seller) {
	
			sellerDao.insert(seller);
		
	}
	public void update(Seller seller) {
		
			sellerDao.update(seller);
		
	}
	public void remove(Seller seller) {
		sellerDao.deleteById(seller.getId());
	}
}
