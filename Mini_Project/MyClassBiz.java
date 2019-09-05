package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyClassBiz {
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;

	public HashMap<String, Object> myLecs(int div, int pgNum, String memPk, String sCondition) {
		final int PGSIZE = 10;
		int startRow = 1;
		int rowLimit;
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ArrayList<LecListDto> arrL = null;
		int msgCode;
		String isNext = "N";
		String isErr = "Y";
		
		startRow = (pgNum-1)*PGSIZE + 1;
		rowLimit = PGSIZE + 1;

		arrL = DaoMthdSelect(div, startRow, rowLimit,  memPk, sCondition);

		int size = arrL.size();
		if(size == 0 && div == 0)
		{
			msgCode = 206;//"검색조건에 해당되는 강좌가 없습니다.";		
		}
		else if(size == 0)
		{
			msgCode = 200;//"수강 중인 강의가 없습니다.";
		}
		else
		{
			msgCode = 201;//"정상조회 되었습니다.";
			isErr = "N";
			if(size == 11)
			{
				arrL.remove(10);
				isNext = "Y";
			}
		}

		hm.put("ARRAYLIST", arrL);
		hm.put("MSGCODE", msgCode);
		hm.put("ISNEXT", isNext);
		hm.put("ISERR", isErr);

		return hm;
	}

	public ArrayList<LecListDto> DaoMthdSelect(int div, int startRow, int rowLimit, String memPk, String sCondition)
	{
		ArrayList<LecListDto> arrL = null;
		
		final int ENTIRE = 0;
		final int TITLE = 1;
		final int TUTOR = 2;
		final int CTGRY = 3;
		
		try {
			con = Common.connect();
			DaoRMyClass dao = new DaoRMyClass(con, pstmt, rs);
			
			if(div == ENTIRE)
			{
				arrL = dao.entire(memPk, startRow, rowLimit);
			}
			else if (div == TITLE)
			{
				arrL = dao.title(memPk, startRow, rowLimit, sCondition);
			}
			else if (div == TUTOR)
			{
				arrL = dao.tutor(memPk, startRow, rowLimit, sCondition);
			}
			else if (div == CTGRY)
			{
				int ctgryPk = Integer.parseInt(sCondition);
				arrL = dao.ctgry(memPk, startRow, rowLimit, ctgryPk);
			}
			
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrL;
	}

}
