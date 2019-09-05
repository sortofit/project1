package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LecOpenBiz {
	
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
	
	public HashMap<String, Object> lecInsert(LecDto lecDto) //강좌등록
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		String ctgry = lecDto.getCtgr();
		
		int ret = 0 ;
		LecDto sameLecDto;
		try {
			con = Common.connect();
			DaoLecture dao = new DaoLecture(con, pstmt, rs);
			sameLecDto = dao.lecSltOne(lecDto);
		
			if(sameLecDto == null)	//동일튜터,동일강좌 없음
			{
				String ctgryPk = dao.ctgrySlt(ctgry);
				
				int lecCodeMax = dao.lecCodeMax() + 1;
				String lecPk = String.valueOf(lecCodeMax);
				
				lecDto.setCtgr(ctgryPk);
				lecDto.setLecPk(lecPk);
				ret = dao.lecInsert(lecDto); //처리결과 반환
				
				if(ret > 0)
				{
					hm.put("MSGCODE", 105);	//강좌등록 정상처리
				}
				else
				{
					hm.put("MSGCODE", 199);	//오류
				}
			}
			else if(sameLecDto != null)
			{
				hm.put("MSGCODE", 106);
			}
			
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return hm;
	}
	
}
