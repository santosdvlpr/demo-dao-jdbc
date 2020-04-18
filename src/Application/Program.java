package Application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.*;
public class Program {

	public static void main(String[] args) {
		
		Department obj1 = new Department(1,"Books");
		Seller obj2 = new Seller(1,"Henzo P Santana","henzo@hotmail.com",new Date(),8000.00,obj1);

		SellerDao sellerDao = DaoFactory.createSellerDao(); 
		
		System.out.println(obj2);

	}

}
