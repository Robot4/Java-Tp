package com.emsi.Maven.JDBC.dao;

import java.util.List;

import com.emsi.Maven.JDBC.entities.Department;
import com.emsi.Maven.JDBC.entities.Seller;

public interface SellerDao {
	void insert(Seller seller);

	void update(Seller seller);

	void deleteById(Integer id);

	Seller findById(Integer id);

	List<Seller> findAll();

	List<Seller> findByDepartment(Department department);
}
