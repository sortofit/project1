package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCategory {
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;

	public DaoCategory(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}

	public ArrayList<String> ctgryList() throws SQLException  {
		ArrayList<String> ctgryArrL = new ArrayList<String>();

		String sSelect = "select * from category " 
					   + "order by ctgry_pk      ";

		pstmt = con.prepareStatement(sSelect);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			String ctgry = rs.getString("ctgry_name");
			ctgryArrL.add(ctgry);
		}
		
		return ctgryArrL;
	}
}
