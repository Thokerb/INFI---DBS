//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;



public class SecondExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql://localhost/dbp";

   //  Database credentials
   static final String USER = "postgres";
   static final String PASS = "password";
   
   public static void main(String[] args) {
	  //STEP 1: Initialize variables
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
// hinzufügen löschen verändern lesen
      Scanner scan = new Scanner(System.in);
      boolean running = true;
      
      while(running){
          System.out.println("Geben Sie an welche Operation Sie durchführen möchten (C - Create, R - Read, U - Update, D - Delete, E - End)");
          String action = scan.next();
          
          switch(action){
          case "C":{
        	  System.out.println("Geben Sie bitte den Vornamen an!");
              String vn = scan.next();
        	  System.out.println("Geben Sie bitte den Nachnamen an!");
              String nn = scan.next();
        	  System.out.println("Geben Sie bitte das Alter an!");
              String alter = scan.next();
              String sql = "INSERT INTO Employee (first,last,age) VALUES ('"+vn+"','"+nn+"',"+alter+ ")";
              System.out.println("Befehl wird generiert: "+sql);
              if(IsInteger(vn)||IsInteger(nn)|| !IsInteger(alter)){
            	  System.out.println("fehlerhafte Eingabe!");
              }else{
                  stmt = conn.createStatement();
                  stmt.executeUpdate(sql);
                  closer(stmt);
              }

        	  break;
          }
          case "R":{
              stmt = conn.createStatement();
              String sql = "SELECT id, first, last, age FROM Employee";
              ResultSet rs = stmt.executeQuery(sql);
              
              while(rs.next()){
                  //Retrieve by column name
                  int id  = rs.getInt("id");
                  int age = rs.getInt("age");
                  String first = rs.getString("first");
                  String last = rs.getString("last");

                  //Display values
                  System.out.print("ID: " + id);
                  System.out.print(", Age: " + age);
                  System.out.print(", First: " + first);
                  System.out.println(", Last: " + last);
               }
              
              closer(rs, stmt);

        	  break;
          }
          case "U":{
              stmt = conn.createStatement();
         	  boolean nixgebn = true;
        	  String ID = null;
        	  while(nixgebn){
            	  System.out.println("Geben Sie bitte die ID an, welche Sie updaten möchten");
            	  ID = scan.next();
            	  if(IsInteger(ID)){
            		  nixgebn = false;
            	  }
            	  else{
            		  System.out.println("Die ID ist ungültig");
            	  }
        	  }
        	  
        	  System.out.println("Geben Sie bitte die neuen Werte an");
        	  System.out.println("Geben Sie bitte den Vornamen an!");
              String vn = scan.next();
        	  System.out.println("Geben Sie bitte den Nachnamen an!");
              String nn = scan.next();
        	  System.out.println("Geben Sie bitte das Alter an!");
              String alter = scan.next();
              
              String sql = "UPDATE Employee "
            		  		+"SET first = \'"+ vn  +"\', last = \'"+ nn  +"\', age = \'"+ alter  +"\' "
            		  		+"WHERE id = \'"+ ID  +"\'";
        	  System.out.println(sql);
        	  stmt.executeUpdate(sql);
        	  break;
          }
          case "D":{
        	  stmt = conn.createStatement();
        	  boolean nixgebn = true;
        	  String ID = null;
        	  while(nixgebn){
            	  System.out.println("Geben Sie bitte die ID an, welche Sie löschen möchten");
            	  ID = scan.next();
            	  if(IsInteger(ID)){
            		  nixgebn = false;
            	  }
            	  else{
            		  System.out.println("Die ID ist ungültig");
            	  }
        	  }
        	  
        	  String sql = "DELETE FROM Employee WHERE id = \'"+ID+"\'";
        	  System.out.println(sql);
        	  stmt.executeUpdate(sql);
        	  closer(stmt);
        	  break;
          }
          case "E":{
        	  System.out.println("Verbindung mit der Datenbank wird beendet.");
        	  running = false;
        	  closer(conn);
        	  break;
          }
          default:{
        	  System.out.println("Die Aktion ist nicht korrekt. Bitte korrigieren Sie Ihre Eingabe!" );
          }
          
          }
      }

      
      String vn = scan.next();
      String nn = scan.next();
      String alter = scan.next();
      
      //STEP 4: Execute a query
   //   String vn = "Sepp";
     // String nn = "Huber";
      //int alter = 21;
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql1;
      String sql;
      sql1 = "SELECT id, first, last, age FROM Employee";
//      sql = "CREATE TABLE Employee ("
//    		+ "id SERIAL primary key,"
//      		+ "first character varying not null," 
//      		+ "last character varying not null,"
//      		+ "age int)";
      
      sql = "INSERT INTO Employee (first,last,age) VALUES ('"+vn+"','"+nn+"',"+alter+ ")";
      ResultSet rs = stmt.executeQuery(sql1);
      ResultSet rs2 = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Age: " + age);
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
   
   public static void closer(Connection conn){
	   try {

		      conn.close();
	   }catch (Exception e) {
		e.printStackTrace();
	}

   }
   public static void closer(ResultSet rs, Statement stmt){
	   try {
		      rs.close();
		      stmt.close();
	   }catch (Exception e) {
		e.printStackTrace();
	}

   }
   
   public static boolean IsInteger(String str)
   {
       try
       {
           Integer.parseInt(str);
           return true;
       }
       catch(NumberFormatException nfe)
       {
           return false;
       }
   }
   
   public static void closer( Statement stmt){
	   try {
		      stmt.close();
	   }catch (Exception e) {
		e.printStackTrace();
	}

   }
   
   
}//end FirstExample