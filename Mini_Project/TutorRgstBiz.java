package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TutorRgstBiz {
	
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
	private DaoTutor dao;
	
	public HashMap<String, Object> nicknameSlt(String tutorNick)	//닉네임중복조회
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		String nickname = null;
		
		try {
			con = Common.connect();
			dao = new DaoTutor(con, pstmt, rs);
			nickname = dao.nicknameSlt(tutorNick);
			Common.close(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		if( nickname != null )	// 중복
		{
			hm.put("MSGCODE", 102);
		}
		else	// 사용가능
		{
			hm.put("MSGCODE", 103);
		}		
		return hm;
	}
	
	public HashMap<String, Object> tutorInsert(TutorDto tutorDto) //튜터등록
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		try {
			con = Common.connect();
			dao = new DaoTutor(con, pstmt, rs);
		
			int ret = dao.tutorInsert(tutorDto);
			
			if( ret > 0 )	// 정상등록
			{
				String memPk = tutorDto.getTutorPk();
				int    cnt	 = dao.isTutorUpdate(memPk);
				
				if(cnt > 0)
				{
					System.out.println("isTutor : N → Y 변경");
				}
				
				hm.put("MSGCODE", 104);			
				hm.put("ISTUTOR", "Y");
			}
			else
			{
				hm.put("MSGCODE", 199);
				hm.put("ISTUTOR", "N");
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
