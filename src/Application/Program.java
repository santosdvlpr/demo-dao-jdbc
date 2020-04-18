package Application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.*;
public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao(); 
		
		Seller seller = sellerDao.findById(6); 
		
		System.out.println("===  Teste N� 1 FindById ===");
		System.out.println(seller);
		
		System.out.println("\n===  Teste N� 2 FindByDepartment ===");
		Department dep = new Department(2,null);
		List<Seller> sellers = sellerDao.findByDepartment(dep);
		for (Seller obj : sellers) {
			System.out.println(obj);
		}
	}

}
