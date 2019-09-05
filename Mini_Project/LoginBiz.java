package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginBiz {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public HashMap<String, Object> confirmPW(MemDto memDto) // 로그인정보찾기 아이디/패스워드 조회
	{
		
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		MemDto dtoDB = null;

		try {
		con = Common.connect();
		DaoMembers dao = new DaoMembers(con, pstmt, rs);
		dtoDB = dao.sltOneEmailPw(memDto);
		Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		if (dtoDB == null) 
		{
			
			hm.put("ISPWSAME", "N");
			hm.put("MSGCODE", 1);       //"아이디 및 비밀번호를 잘못 입력하셨습니다."
		}
		else {
			hm.put("ISPWSAME", "Y");
			hm.put("MSGCODE", 2);      //"로그인 성공."
		}
		    hm.put("MEMDTO", dtoDB);

		return hm;
	}
}
