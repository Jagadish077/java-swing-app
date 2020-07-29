package mainApp;

import java.util.Scanner;

public class TestApp {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the username");
		String username = s.nextLine();
		System.out.println("Enter the password");
		int password = s.nextInt();
		
		if(password==123){
			new MobileList().setVisible(true);
		}else{
			System.out.println("invalid username and password");
		}
	}
}
