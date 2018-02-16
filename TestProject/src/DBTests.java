import static org.junit.Assert.*;

import java.sql.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTests {	
	  Connection con = null;
	  Testclass c;

	@Test
	public void LoadDB(){
		try {
			c = new Testclass();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			fail("Failed to get Instance of Driver");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			fail("Failed to load JDBC Driver");
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void TestDBConnection(){
		
		try {  
		  
			 con = c.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("Connection failed");
			e.printStackTrace();
		} 
		System.out.println("Connection: "+con);
		assertTrue(con!= null);
	}
	
	@Test
	public void TestInsertFS() throws InstantiationException, IllegalAccessException{
		try {
			Testclass c = new Testclass();
			Connection con = c.getConnection();
			c.InsertFS("TestFahrschule", con);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}
	
	@Test
	public void TestFahrschule_Standort(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			c.Fahrschule_Ort(3, 6060, con);

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void TestInsertOrt(){

		try {
			Testclass c = new Testclass();
			Connection con = c.getConnection();
			c.InsertOrt(6060, "Hall", "Sparberegg", 13, "Österreich", con);
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}
	
	@Test
	public void TestdeleteOrt()  {
		
			Testclass c;
			try {
				c = new Testclass();
				Connection con = c.getConnection();
				c.deleteOrt(6060, con);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// TODO Auto-generated catch block
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
			}

		
	}
	
	@Test
	public void TestdeleteFS(){
		
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			c.deleteFahrschule(3, con);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	
}
	
	@Test
	public void TestdeleteOrt_FS(){
		Testclass c;
			try {
				c = new Testclass();
				Connection con = c.getConnection();
				c.deleteFahrschuleStandort_FS(3, con);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

	@Test
	public void TestdeleteOrt_PLZ(){
		Testclass c;
			try {
				c = new Testclass();
				Connection con = c.getConnection();
				c.deleteFahrschuleStandort_PLZ(6060, con);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
			}


	}
	
	@Test
	public void TestFahrschulenSelect(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			assertTrue(c.getFahrschulen(3,con)!=null);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void TestFahrschulenSelect2(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			assertTrue(c.getFahrschulen(con)!=null);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void TestOrtSelect(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			assertTrue(c.getOrte(con)!=null);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void TestOrtSelect2(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			assertTrue(c.getOrte(6060,con)!=null);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void TestFSOrt(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			assertTrue(c.getFahrschulenOrte(6060, con)!=null);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void TestFSOrt2(){
		Testclass c;
		try {
			c = new Testclass();
			Connection con = c.getConnection();
			assertTrue(c.getOrteFahrschule(3, con)!=null);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}

}
