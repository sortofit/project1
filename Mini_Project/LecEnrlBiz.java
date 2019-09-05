package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LecEnrlBiz {
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;

	public HashMap<String, Object> lecs(int div, int pgNum, String sCondition) // 수강신청용 강좌목록보기
	{
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

		arrL = DaoMthdSelect(div, startRow, rowLimit, sCondition);
		
		int size = arrL.size();
		if(size == 0 && div == 0)
		{
			msgCode = 207;//"개설된 강의가 없습니다.";
		}
		else if(size == 0)
		{
			msgCode = 206; //"검색조건에 해당되는 강좌가 없습니다.";		
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

	public HashMap<String, Object> lecEnrl(EnrlDto enrlDto) // 수강신청
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		int cnt = 0;
		int msgCode;
		EnrlDto dtoDB = null;
		
		try {
			con = Common.connect();
			DaoEnroll dao = new DaoEnroll(con, pstmt, rs);
			dtoDB = dao.sltOne(enrlDto);
			if (dtoDB == null) {
				cnt = dao.insert(enrlDto);
			}
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if( dtoDB != null)
		{
			msgCode = 204; //"이미 수강중인 강좌입니다.";
		}
		else if (cnt == 1) 
		{
			msgCode = 205; //"정상 등록 되었습니다."; 
		} 
		else 
		{
			msgCode = 208; //"수강신청 중 오류가 발생하였습니다. 관리자에게 연락바랍니다."; 
		}

		hm.put("CNT", cnt);
		hm.put("MSGCODE", msgCode);

		return hm;
	}
	
	public ArrayList<LecListDto> DaoMthdSelect(int div, int startRow, int rowLimit, String sCondition)
	{
		ArrayList<LecListDto> arrL = null;
		
		final int ENTIRE = 0;
		final int TITLE = 1;
		final int TUTOR = 2;
		final int CTGRY = 3;
		
		try {
			con = Common.connect();
			DaoRLecEnrl dao = new DaoRLecEnrl(con, pstmt, rs);
			
			if(div == ENTIRE)
			{
				arrL = dao.entire(startRow, rowLimit);
			}
			else if (div == TITLE)
			{
				arrL = dao.title(startRow, rowLimit, sCondition);
			}
			else if (div == TUTOR)
			{
				arrL = dao.tutor(startRow, rowLimit, sCondition);
			}
			else if (div == CTGRY)
			{
				int ctgryPk = Integer.parseInt(sCondition);
				arrL = dao.ctgry(startRow, rowLimit, ctgryPk);
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
