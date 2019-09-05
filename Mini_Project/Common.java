package Mini_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Common {
	private static HashMap<String, Object> hm = new HashMap<String, Object>();
	private static HashMap<Integer, String> hmMsgCode = null;
	
	public static HashMap<String, Object> getHm(){
		return hm;
	}
	
	public static HashMap<Integer, String> getHmMsgCode() {
		
		if(hmMsgCode == null)
		{
			hmMsgCode = new HashMap<Integer, String>();
			
			
			hmMsgCode.put(1,"아이디 및 비밀번호를 잘못 입력하셨습니다.");                       
			hmMsgCode.put(2,"로그인 성공.");                                                 
			hmMsgCode.put(3,"이미 존재하는 이메일입니다.");                                   
			hmMsgCode.put(4,"사용할 수 있는 이메일입니다.");                                    
			hmMsgCode.put(5,"이미 존재하는 회원 입니다.");                                       
			hmMsgCode.put(6,"회원 가입이 완료 되었습니다.");                                     
//			hmMsgCode.put(7,"데이터베이스에 등록 중 오류가 발생하였습니다. 관리자에게 문의해 주십시오.");
			
			hmMsgCode.put(101, "별명을 입력해 주세요." );
			hmMsgCode.put(102, "이미 존재하는 별명입니다.");
			hmMsgCode.put(103, "사용할 수 있는 별명입니다.");
			hmMsgCode.put(104, "튜터등록을 완료했습니다.");
			hmMsgCode.put(105, "강좌등록을 완료했습니다.");
			hmMsgCode.put(106, "동일한 강좌를 이미 등록했습니다.");
			hmMsgCode.put(107, "비밀번호가 정상적으로 확인되었습니다.");
			hmMsgCode.put(108, "비밀번호가 일치하지 않습니다.");
			hmMsgCode.put(109, "해당회원이 존재하지 않습니다.");
			hmMsgCode.put(110, "정상수정 되었습니다.");
			
			hmMsgCode.put(199, "데이터베이스 오류. 관리자에게 문의바랍니다.");
			
			hmMsgCode.put(200, "수강 중인 강의가 없습니다.");
			hmMsgCode.put(201, "정상조회 되었습니다.");
			hmMsgCode.put(202, "해당되는 강좌가 없습니다.");
			hmMsgCode.put(203, "강좌목록이 성공적으로 조회되었습니다.");
			hmMsgCode.put(204, "이미 수강중인 강좌입니다.");
			hmMsgCode.put(205, "정상 등록 되었습니다.");
			hmMsgCode.put(206, "검색조건에 해당되는 강좌가 없습니다.");
			hmMsgCode.put(207, "개설된 강의가 없습니다.");
			hmMsgCode.put(208, "수강신청 중 오류가 발생하였습니다. 관리자에게 연락바랍니다.");
		}
		
		return hmMsgCode;
	}
	
	public static Connection connect() throws ClassNotFoundException, SQLException
	{
		String driver   = "oracle.jdbc.driver.OracleDriver";
	  	String url      = "jdbc:oracle:thin:@localhost:1521:XE";
	  	String user     = "team";
	  	String password = "123456";
	  	   
	  	Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException
	{
		if( rs != null ) con.close();  
		if( pstmt != null ) con.close();
		if( con != null ) con.close();
	}
}
