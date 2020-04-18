package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs,department);
				return seller;
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

	private Seller instantiateSeller(ResultSet rs,Department department) throws SQLException {

		Seller obj = new Seller();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setBirthdate(rs.getDate("birthdate"));
		obj.setBasesalary(rs.getDouble("basesalary"));
		obj.setDepartment(department);
		return obj;
	}


	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("department_id"));
		department.setName(rs.getString("depname"));
		
		return department;
	}


	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st =
			  conn.prepareStatement(  
			 " SELECT seller.*,department.name as depname " + 
			 " FROM seller INNER JOIN department " + 
			 " ON seller.department_id = department.id " + 
			 " WHERE department_id = ? ORDER BY name ");  
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Seller> sellers = instantiateSellers(rs,department);
			return sellers;
		}
		catch(SQLException e) {
			
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}


	private List<Seller> instantiateSellers(ResultSet rs, Department department) throws SQLException {

		List<Seller> sellers = new ArrayList<>();
		Map<Integer, Department> map = new HashMap<>();
		int x = 0;
		while(rs.next()) {
			x=+1;
			Department dep = map.get(rs.getInt("department_id")); // instancia departamneto j� mapeado em map 
			
			if(dep == null) {
				dep = instantiateDepartment(rs);
				map.put(rs.getInt("department_id"), dep);
			}
			Seller obj = instantiateSeller(rs, dep);
			sellers.add(obj);
		}
		return sellers;
	}

	
	
}
