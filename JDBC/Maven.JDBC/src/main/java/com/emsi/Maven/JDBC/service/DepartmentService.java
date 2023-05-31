package com.emsi.Maven.JDBC.service;

import java.util.List;

import com.emsi.Maven.JDBC.dao.DepartmentDao;
import com.emsi.Maven.JDBC.dao.impl.DepartmentDaoImp;
import com.emsi.Maven.JDBC.entities.Department;

public class DepartmentService {
	private DepartmentDao departmentDao = new DepartmentDaoImp();

	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	public void save(Department department) {
		
			departmentDao.insert(department);
		
	}
	public void update(Department department) {
		
			departmentDao.update(department);
		
	}
	public void remove(Department department) {
		departmentDao.deleteById(department.getId());
	}
}
