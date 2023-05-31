package com.emsi.Maven.JDBC.dao;

import java.util.List;

import com.emsi.Maven.JDBC.entities.Department;

public interface DepartmentDao {
	void insert(Department department);

	void update(Department department);

	void deleteById(Integer id);

	Department findById(Integer id);

	List<Department> findAll();
}
