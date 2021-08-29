package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public static String getDataFromDatabase(String columnName) throws SQLException, ClassNotFoundException {
		String item = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fahim", "root", "root");
		//Creates a {@code Statement} object for sending
	    //SQL statements to the database
		Statement smt = con.createStatement();
		
		// Executes the given SQL statement, which returns a single
	    // {@code ResultSet} object
		ResultSet rs = smt.executeQuery("select * from information");
		
		//Moves the cursor forward one row from its current position.
	    //A {@code ResultSet} cursor is initially positioned
	    //before the first row; the first call to the method
	    //{@code next} makes the first row the current row; the
	    //second call makes the second row the current row, and so on
		while(rs.next()) {
			item = rs.getString(columnName);
		}
		System.out.println(item +"-----------------------");
		return item;
	}
	
}
