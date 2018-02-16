import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Testclass {
		
	   static final String DB_URL = "jdbc:mysql://localhost:3306/Project2?user=root&password=&useSSL=false";

	   //  Database credentials


	   
	   Connection conn = null;
	   Statement stmt = null;
	   PreparedStatement pstmt = null;

	   public Testclass() throws InstantiationException, IllegalAccessException{
			super();
			// DB Driver init
			try {
				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public static Connection getConnection() throws SQLException
		{
			Connection conn = null;
			//neuen Connection holen
			try {
				conn=DriverManager.getConnection(DB_URL);


			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
			return conn;
		}

		public void releaseConnection(Connection conn)
		{
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				System.out.println("Fehler beim schlieﬂen der Verbindung:");
				System.out.println("Meldung: "+e.getMessage());
				e.printStackTrace();
			}
		}
		
		public void InsertFS(String FS,Connection conn) throws SQLException{
			String SQL = "INSERT INTO Fahrschule (Name) VALUES ('"+FS+"');";
				stmt = conn.createStatement();
				stmt.executeUpdate(SQL);
				stmt.close();
		}
		
		public void InsertOrt(int PLZ,String Ortsname,String Strasse,int HNR,String Land,Connection conn) throws SQLException{
			String SQL = "INSERT INTO ort (PLZ,Ortsname,Strasse,Hausnr,Land) VALUES (?,?,?,?,?);";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, PLZ);
				pstmt.setString(2,Ortsname);
				pstmt.setString(3,Strasse);
				pstmt.setInt(4,HNR);
				pstmt.setString(5,Land);
				pstmt.executeUpdate();
				pstmt.close();
		}
		
		public void Fahrschule_Ort(int FahrschulID,int PLZ,Connection con) throws SQLException{
			String SQL = "INSERT INTO fahrschulstandort (FahrschulID,PLZ) VALUES (?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, FahrschulID);
			pstmt.setInt(2, PLZ);
			pstmt.executeUpdate();
			pstmt.close();
		
		}
		
		public void deleteFahrschule(int FahrschulID,Connection conn) throws SQLException{
			String SQL = "DELETE FROM fahrschule WHERE FahrschulID = '"+FahrschulID+"'";
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		}
		
		public void deleteOrt(int PLZ,Connection conn) throws SQLException{
			String SQL = "DELETE FROM ort WHERE PLZ = '"+PLZ+"'";
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		}
		
		public void deleteFahrschuleStandort_PLZ(int PLZ,Connection conn) throws SQLException{
			String SQL = "DELETE FROM fahrschulstandort WHERE PLZ = '"+PLZ+"'";
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		}
		
		public void deleteFahrschuleStandort_FS(int FahrschulID,Connection con) throws SQLException{
			String SQL = "DELETE FROM fahrschulstandort WHERE FahrschulID = '"+FahrschulID+"'";
			stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		}
		
		public String[][] getFahrschulen(int ID,Connection con) throws SQLException{
			String SQL = "Select * from fahrschule WHERE FahrschulID = '"+ID+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println(rs.getFetchSize());
			String[][] daten = new String[rs.getFetchSize()+1][2];
			int a = 0;
			while(rs.next()){
				System.out.println(rs.getString(1));
				daten[a][0] = rs.getString(1);
				daten[a][1] = rs.getString(2);
				System.out.println(daten[a][0]+" | "+daten[a][1]);
				a++;
			}
			return daten;
		}
		
		public String[][] getFahrschulen(Connection con) throws SQLException{
			String SQL = "Select * from fahrschule";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println(rs.getFetchSize());
			String[][] daten = new String[rs.getFetchSize()+1][2];
			int a = 0;
			while(rs.next()){
				System.out.println(rs.getString(1));
				daten[a][0] = rs.getString(1);
				daten[a][1] = rs.getString(2);
				System.out.println(daten[a][0]+" | "+daten[a][1]);
				a++;
			}
			return daten;
		}
		
		public String[][] getOrte(int PLZ,Connection con) throws SQLException{
			String SQL = "Select * from ort WHERE PLZ = '"+PLZ+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println(rs.getFetchSize());
			String[][] daten = new String[rs.getFetchSize()+1][5];
			int a = 0;
			while(rs.next()){
				System.out.println(rs.getString(1));
				daten[a][0] = rs.getString(1);
				daten[a][1] = rs.getString(2);
				daten[a][2] = rs.getString(3);
				daten[a][3] = rs.getString(4);
				daten[a][4] = rs.getString(5);
				System.out.println(daten[a][0]+" | "+daten[a][1]+" | "+daten[a][2]+" | "+daten[a][3]+" | "+daten[a][4]);
				a++;
			}
			return daten;
		}
		
		public String[][] getOrte(Connection con) throws SQLException{
			String SQL = "Select * from ort";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println(rs.getFetchSize());
			String[][] daten = new String[rs.getFetchSize()+1][5];
			int a = 0;
			while(rs.next()){
				System.out.println(rs.getString(1));
				daten[a][0] = rs.getString(1);
				daten[a][1] = rs.getString(2);
				daten[a][2] = rs.getString(3);
				daten[a][3] = rs.getString(4);
				daten[a][4] = rs.getString(5);
				System.out.println(daten[a][0]+" | "+daten[a][1]+" | "+daten[a][2]+" | "+daten[a][3]+" | "+daten[a][4]);
				a++;
			}
			return daten;
		}
		
		public String[][] getFahrschulenOrte(int PLZ,Connection con) throws SQLException{
			String SQL = "Select * from fahrschulstandort WHERE PLZ = '"+PLZ+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println(rs.getFetchSize());
			String[][] daten = new String[rs.getFetchSize()+1][2];
			int a = 0;
			while(rs.next()){
				System.out.println(rs.getString(1));
				daten[a][0] = rs.getString(1);
				daten[a][1] = rs.getString(2);
				System.out.println(daten[a][0]+" | "+daten[a][1]);
				a++;
			}
			return daten;
		}
		
		public String[][] getOrteFahrschule(int ID,Connection con) throws SQLException{
			String SQL = "Select * from fahrschulstandort WHERE FahrschulID = '"+ID+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println(rs.getFetchSize());
			String[][] daten = new String[rs.getFetchSize()+1][2];
			int a = 0;
			while(rs.next()){
				System.out.println(rs.getString(1));
				daten[a][0] = rs.getString(1);
				daten[a][1] = rs.getString(2);
				System.out.println(daten[a][0]+" | "+daten[a][1]);
				a++;
			}
			return daten;
		}
	
}
