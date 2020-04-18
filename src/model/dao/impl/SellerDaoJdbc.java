package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJdbc implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJdbc(Connection conn) {
		
		this.conn = conn;
		
	}
	
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( 
			 " SELECT seller.*,department.name as depname  " +
			 " FROM seller INNER JOIN department  ON seller.department_id = department_id" +
			 " WHERE seller.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
			
				Department department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setName(rs.getString("depname"));
				Seller obj = new Seller();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setEmail(rs.getString("email"));
				obj.setBirthdate(rs.getDate("birthdate"));
				obj.setBasesalary(rs.getDouble("basesalary"));
				obj.setDepartment(department);
				return obj;
			}
			return null;
		}
		catch(SQLException e) {
			
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}