package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MemberJoinBiz {

	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
	
	DaoMembers dao;
	
	public HashMap<String, Object> confirmEmail(String email) {         //회원 가입시 email 중복 여부 조회
		HashMap<String, Object> hm = new HashMap<String, Object>();
		MemDto dtoDB = null;
		
		try {
			con = Common.connect();
			DaoMembers dao = new DaoMembers(con, pstmt, rs);
			dtoDB = dao.sltOne(email); // 중복확인
			Common.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (dtoDB != null) 
		{		
			hm.put("ISEMAILOK", "N");
			hm.put("MSGCODE", 3);	       //"이미 존재하는 이메일입니다."
		} 
		else 
		{
			hm.put("ISEMAILOK", "Y");
			hm.put("MSGCODE", 4);		   //"사용할 수 있는 이메일입니다."	  		
		}
		
		return hm;
	}

	public HashMap<String, Object> memJoin(MemDto dto) {             //회원 가입 insert

		HashMap<String, Object> hm = new HashMap<String, Object>();
		MemDto dto2 = null;

		String memPk = dto.getMemPk();
		String email = dto.getEmail();
		String pw = dto.getPw();
		String add = dto.getAdd();
		String tel = dto.getTel();
		String isTutor = dto.getIsTutor();
		int ret = 0;

		try {
			con = Common.connect();
			DaoMembers dao = new DaoMembers(con, pstmt, rs);
		
			dto2 = dao.sltOne(email);
			
			if (dto2 == null) {
				memPk = memPkGenn();
				dto.setMemPk(memPk);
				dto.setEmail(email);
				dto.setPw(pw);
				dto.setTel(tel);
				dto.setAdd(add);
				dto.setIsTutor(isTutor);
				
				ret = dao.insert(dto);		
			}
			
			Common.close(con, pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (dto2 != null) {
			hm.put("MSGCODE", 5);       //"이미 존재하는 회원 입니다."
			hm.put("CNT", ret);
		}
		else if (ret == 1) 
		{
			hm.put("MSGCODE", 6);      //"회원 가입이 완료 되었습니다."
			hm.put("CNT", ret);		
		}
		else
		{			
			hm.put("MSGCODE", 199);     //"데이터베이스에 등록 중 오류가 발생하였습니다. 관리자에게 문의해 주십시오."
			hm.put("CNT", ret);						
		}
		
		return hm;

	}

	public String memPkGenn() {           //max값 구해서 회원번호 부여
		
		String memPk = null;
		
		try {			
			con = Common.connect();
			DaoMembers dao = new DaoMembers(con, pstmt, rs);
			String maxPk = dao.memMaxPk();
			Common.close(con, pstmt, rs);
			if (maxPk == null) 
			{
				maxPk = "-1";
			}

		memPk = String.valueOf(Integer.parseInt(maxPk) + 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
				
		return memPk;
	}

}
