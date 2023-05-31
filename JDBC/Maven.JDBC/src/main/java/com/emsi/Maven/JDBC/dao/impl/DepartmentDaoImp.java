package com.emsi.Maven.JDBC.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emsi.Maven.JDBC.dao.DepartmentDao;
import com.emsi.Maven.JDBC.entities.Department;

public class DepartmentDaoImp implements DepartmentDao {

	private Connection conn= DB.getConnection();

	@Override
	public void insert(Department department) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, department.getName());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					department.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un département");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");

			ps.setString(1, department.getName());
			ps.setInt(2, department.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un département");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un département");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM department WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Department department = new Department();

				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));

				return department;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver un département");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM department");
			rs = ps.executeQuery();

			List<Department> listDepartment = new ArrayList<>();

			while (rs.next()) {
				Department department = new Department();

				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));

				listDepartment.add(department);
			}

			return listDepartment;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner un département");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

}
