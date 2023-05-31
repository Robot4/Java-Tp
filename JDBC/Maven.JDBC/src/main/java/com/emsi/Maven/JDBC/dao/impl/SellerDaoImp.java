package com.emsi.Maven.JDBC.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emsi.Maven.JDBC.dao.SellerDao;
import com.emsi.Maven.JDBC.entities.Department;
import com.emsi.Maven.JDBC.entities.Seller;

public class SellerDaoImp implements SellerDao {

	private Connection conn= DB.getConnection();

	

	@Override
	public void insert(Seller seller) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());
			

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					seller.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyé");;
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un vendeur");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Seller seller) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? WHERE Id = ?");

			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());
			ps.setInt(6, seller.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un vendeur");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM seller WHERE id = ?");

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un vendeur");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT s.*, d.Name AS DepName FROM seller s INNER JOIN department d ON s.DepartmentId = d.Id WHERE s.id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);

				return seller;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver le vendeur");
			return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT s.*, d.Name as DepName FROM seller s INNER JOIN department d ON s.DepartmentId = d.Id ORDER BY s.Name");
			rs = ps.executeQuery();
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs);

					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = instantiateSeller(rs, dep);

				list.add(seller);
			}

			return list;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner les vendeurs");
		return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT s.*, d.Name as DepName FROM seller s INNER JOIN department d ON s.DepartmentId = d.Id WHERE s.DepartmentId = ? ORDER BY s.Name");

			ps.setInt(1, department.getId());

			rs = ps.executeQuery();
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs);

					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = instantiateSeller(rs, dep);

				list.add(seller);
			}

			return list;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner les vendeurs d'un département donné");
		return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();

		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		seller.setDepartment(department);

		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();

		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));

		return department;
	}

}
