package eDentify;

import java.util.Scanner;

public class CommandLineProcessing extends Main {
	
	Scanner sc = new Scanner(System.in);
	
	public void showOptions(){
		
		System.out.println("+=+=+=+=+=+= Welcome to eDentify! +=+=+=+=+=+=");
		System.out.print("Please enter your full name: ");
		String name = sc.nextLine();
		
		System.out.print("Please enter your county or state: ");
		String county = sc.nextLine();
		
		System.out.print("Please enter you country: ");
		String country = sc.nextLine();
		
		System.out.println("+=+=+=+=+=+= RESULTS +=+=+=+=+=+=");
		System.out.println("Name: " + name + " - " + "County/State: " + county + " - " + "Country: " + country);
		
		setQuery(name, county, country);
	}
	
	public void setQuery(String nameEntered, String countyEntered, String countryEntered){
		name = nameEntered.replaceAll("\\s","/");
		county = countyEntered.replaceAll("\\s","/");
		country = countryEntered.replaceAll("\\s","/");
		
	}
	
}
