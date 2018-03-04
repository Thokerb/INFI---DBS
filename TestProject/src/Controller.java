import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Testclass test = null;
		try {
			test = new Testclass();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Scanner scan = new Scanner(System.in);
	    
		while(true){
			System.out.println("Geben Sie an welche Operation Sie durchführen möchten (C - Create, R - Read, U - Update, D - Delete)");
	        String action = scan.next();
	        
	        switch(action){
	        case "C":{
	        	System.out.println("Welche Tabelle willst du bearbeiten? (f-fahrschule,o-ort,fs- fahrschulstandort");
	        	Scanner sc = new Scanner(System.in);
	        	String action2 = sc.nextLine();
	        	switch(action2){
	        	case "f":{
	        		System.out.println("Gib einen Fahrschulnamen an");
	            	Scanner sc2 = new Scanner(System.in);
		        	String FS = sc2.nextLine();
	        		try {
						Connection con = test.getConnection();
						test.InsertFS(FS, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        	}
	        	case "o":{
	        		System.out.println("Gib einen PLZ an");
	        		String PLZ1 = scan.next();
	        		int PLZ = Integer.parseInt(PLZ1);
	        		System.out.println("Gib einen Ortsnamen an");
	        		String Ortsname = scan.next();
	        		System.out.println("Gib eine Straße an");
	        		Scanner sc3 = new Scanner(System.in);
	        		String Strasse = sc3.nextLine();
	        		System.out.println("Gib eine HNR an");
	        		String HNR1 = scan.next();
	        		int HNR = Integer.parseInt(HNR1);
	        		System.out.println("Gib ein Land an");
	        		String Land = scan.next();
	        		try {
						Connection con = test.getConnection();
						test.InsertOrt(PLZ, Ortsname, Strasse, HNR, Land, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        	}
	        	case "fs":{
	        		System.out.println("Gib die Fahrschul-ID an");
	        		String FahrschulID1 = scan.next();
	        		int FahrschulID = Integer.parseInt(FahrschulID1);
	        		System.out.println("Gib die OrtID an");
	        		String OrtID1 = scan.next();
	        		int OrtID = Integer.parseInt(OrtID1);
	        		try {
						Connection con = test.getConnection();
						test.Fahrschule_Ort(FahrschulID, OrtID, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        	}
	        	}
	            
	      	  break;
	        }
	        case "R":{
	        	
	        	System.out.println("Welche Tabelle willst du anschauen? (f-fahrschule,o-ort,fs- fahrschulstandort");
	        	String action2 = scan.next();
	        	switch(action2){
	        	case "f":{
	        		
	        		try {
						Connection con = test.getConnection();
						test.getFahrschulen(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        		break;
	        	}
	        	case "o":{
	        		
	        		try {
						Connection con = test.getConnection();
						test.getOrte(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        		break;
	        	}
	        	case "fs":{
	        		
	        		try {
						Connection con = test.getConnection();
		        		System.out.println("Gib die Fahrschul-ID an");
		        		String ID1 = scan.next();
		        		int ID = Integer.parseInt(ID1);
		
						test.getFahrschulenOrte(ID, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        		break;
	        	}
	        	}
	        	
	        	break;
	        }
	        case "U":{
	        	
	        	System.out.println("Welche Tabelle willst du bearbeiten? (f-fahrschule,o-ort");
	        	String action2 = scan.next();
	        	switch(action2){
	        	case "f":{
	        		System.out.println("Gib eine FahrschulID an");
	        		String ID1 = scan.next();
	        		int ID = Integer.parseInt(ID1);
	        		System.out.println("Wie lautet der neue Name?");
	        		String name = scan.next();

	        		try {
						Connection con = test.getConnection();
						test.updateFahrschule(ID, name, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        	}
	        	case "o":{

	        		System.out.println("Gib eine ID an");
	        		String ID1 = scan.next();
	        		int ID = Integer.parseInt(ID1);
	        		System.out.println("Gib einen PLZ an");
	        		String PLZ1 = scan.next();
	        		int PLZ = Integer.parseInt(PLZ1);
	        		System.out.println("Gib einen Ortsnamen an");
	        		String Ortsname = scan.next();
	        		System.out.println("Gib eine Straße an");
	        		String Strasse = scan.next();
	        		System.out.println("Gib eine HNR an");
	        		String HNR1 = scan.next();
	        		int HNR = Integer.parseInt(HNR1);
	        		System.out.println("Gib ein Land an");
	        		String Land = scan.next();
	        		try {
						Connection con = test.getConnection();
						test.updateOrt(ID, Ortsname, Strasse, HNR, Land, PLZ, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        	
	        	}

	        	}
	        	
	        	break;
	        }
	        case "D":{
	        	
	        	System.out.println("Von welcher Tabelle willst du löschen? (f-fahrschule,o-ort,fs- fahrschulstandort");
	        	String action2 = scan.next();
	        	switch(action2){
	        	case "f":{
	        		

	        		System.out.println("Gib eine FahrschulID an");
	        		String FahrschulID1 = scan.next();
	        		int FahrschulID = Integer.parseInt(FahrschulID1);
	        		try {
						Connection con = test.getConnection();
						test.deleteFahrschule(FahrschulID, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;	        	
	        	}
	        	case "o":{
	        		System.out.println("Gib eine OrtID an");
	        		String ID1 = scan.next();
	        		int ID = Integer.parseInt(ID1);
	        		try {
						Connection con = test.getConnection();
						test.deleteOrt(ID, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;	        	
	        	
	        	}
	        	case "fs":{
	        		System.out.println("Gib eine FahrschulID an");
	        		String FahrschulID1 = scan.next();
	        		int FahrschulID = Integer.parseInt(FahrschulID1);
	        		try {
						Connection con = test.getConnection();
						test.deleteFahrschuleStandort_FS(FahrschulID, con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;	        	
	        	
	        	}
	        	}
	        	
	        	break;
	        }

	        default:{
	      	  System.out.println("Die Aktion ist nicht korrekt. Bitte korrigieren Sie Ihre Eingabe!" );
	        }
	        
	        }
		}
        
    
		
	}

}
