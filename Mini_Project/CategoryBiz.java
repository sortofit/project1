package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBiz {
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
	
	public ArrayList<String> getCtgryArray()
	{
		ArrayList<String> arrL = null;
		
		try {
			con = Common.connect();
			DaoCategory dao = new DaoCategory(con, pstmt, rs);
			arrL = dao.ctgryList();
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return arrL;
	}
}
