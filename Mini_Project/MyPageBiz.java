package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MyPageBiz {
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
	
	public HashMap<String, Object> update(MemDto memDto)	//회원정보 수정
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		MemDto memDtoDB = null;
		String email;
		int    ret = 0;
		
		email = memDto.getEmail();
		
		try {
			con = Common.connect();
			DaoMembers dao = new DaoMembers(con, pstmt, rs);
			memDtoDB = dao.sltOne(email);
			if (memDtoDB != null) 
			{
				ret = dao.update(memDto);
			} 
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (memDtoDB == null) 
		{
			hm.put("RET", ret);	
			hm.put("MSGCODE", 109);		//msg = "해당 회원이 존재 하지 않습니다.";
		}
		else if (ret == 1) 
		{
			hm.put("RET", ret);			//ret = 1
			hm.put("MSGCODE", 110);		//msg = "정상수정 되었습니다.";
		} 
		else 
		{	
			hm.put("RET", ret);			//ret = 0
			hm.put("MSGCODE", 199);		//msg = "DB오류. 관리자에게 연락바랍니다.";
		}
		return hm;
	}
	
	public HashMap<String, Object> confirmPwMyPage(MemDto memDto)	//마이페이지 pw확인
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		MemDto memDtoDB = null;
		String isPwSame = "N";

		String memPk 	= memDto.getMemPk();
		String pw 	 	= memDto.getPw();

		try {
			con = Common.connect();
			DaoMembers dao = new DaoMembers(con, pstmt, rs);
			memDtoDB = dao.sltOnePk(memPk); // 중복확인
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (memDtoDB != null) 
		{
			String pwDB = memDtoDB.getPw();

			if (pwDB.equals(pw)) 
			{
				isPwSame = "Y";
				hm.put("MEMDTO", memDtoDB);
				hm.put("ISPWSAME", isPwSame);
				hm.put("MSGCODE", 107); 	//PW 정상확인
			} else {
				hm.put("ISPWSAME", isPwSame);
				hm.put("MSGCODE", 108); 	//PW 불일치
			}
		}
		else
		{
			hm.put("MSGCODE", 199);			//msg = "DB오류. 관리자에게 연락바랍니다."
		}
		return hm;
	}

	public String myPageInitiate(String memPk)		//마이페이지 접속ID 불러오기
	{
		String email;
		MemDto dtoDB = null;

		try {
			con = Common.connect();
			DaoMembers dao = new DaoMembers(con, pstmt, rs);
			dtoDB = dao.sltOnePk(memPk); // 중복확인
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		email = dtoDB.getEmail();

		return email;
	}

}
