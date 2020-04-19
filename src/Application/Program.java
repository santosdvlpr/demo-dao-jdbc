package Application;

import java.util.Date;
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

		System.out.println("\n===  Teste N� 3 FindByAll ===");
		sellers = sellerDao.findAll();
		for (Seller obj : sellers) {
			System.out.println(obj);
		}
		
		System.out.println("\n===  Teste N� 4 Insert ===");
		Seller newSeller = new Seller(null,"Joana Darc C Pinto","joanadarc@hotmail.com",new Date(), 5800.00,dep);
		sellerDao.insert(newSeller);
		System.out.println();
		System.out.printf("Nova chave inserida:%d",newSeller.getId());
		System.out.println();
		
		System.out.println("\n===  Teste N� 5 Update ===");
		seller = sellerDao.findById(8);
		seller.setName("Heitor P Santana");
		seller.setEmail("giselle_cp@hotmail.com");
		sellerDao.update(seller);
		sellers = sellerDao.findAll();
		for (Seller obj : sellers) {
			System.out.println(obj);
		}
	}

}
