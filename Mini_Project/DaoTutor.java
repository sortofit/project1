package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DaoTutor {
	
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
   
   public DaoTutor(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}
               
   public int isTutorUpdate(String memPk)	//isTutor변경 
   {    
	   String update 
	   = "UPDATE MEMBERS 		"
	   + "SET mem_istutor = 'Y' "
	   + "WHERE mem_pk 	  =  ? 	" ;
	   
	   int ret = 0;
	   
	   try
		{
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, memPk);
			
			ret = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return ret;
   }

   public int tutorInsert(TutorDto tutorDto)	//튜터등록 
   {
	   String tutorInsert 
	   = "INSERT INTO TUTOR  (TUTOR_PK, TUTOR_NICK, TUTOR_UNIV, TUTOR_CERT) "
	   + "			  VALUES (?,?,?,?)										" ;
	   
	   int ret = 0;
	   
	   String tutorPk = tutorDto.getTutorPk();	//튜터 코드(PK)
	   String nickname = tutorDto.getNickname();//튜터 닉넴
	   String univ	= tutorDto.getUniv();		//튜터 학력
	   String cert = tutorDto.getCert();		//튜터 자격증
	   
	   try
	   {
		   pstmt = con.prepareStatement(tutorInsert);
	       pstmt.setString(1, tutorPk);
	       pstmt.setString(2, nickname);
	       if (univ !=null)
	    	   pstmt.setString(3, univ);
	       else
	    	   pstmt.setNull(3, java.sql.Types.NULL);
	       if(cert != null)
	    	   pstmt.setString(4, cert);
	       else
	    	   pstmt.setNull(4, java.sql.Types.NULL);
	       
	       ret = pstmt.executeUpdate();
	   } catch (Exception e) {
	       e.printStackTrace();
	   }   
	   return ret;
   }
   
   public String nicknameSlt(String tutorNick)	//튜터닉네임중복조회 
   {
	   String tutorSltOne
	   = "SELECT TUTOR_NICK	   "
	   + "FROM 	 TUTOR 		   "
	   + "WHERE  TUTOR_NICK = ?";
	   
	   String nickname = null;
	   
	   try
	   {
		   pstmt = con.prepareStatement(tutorSltOne);
		   pstmt.setString(1, tutorNick);
		   rs = pstmt.executeQuery();
		   
		   if( rs.next() )
		   {  
			   nickname = rs.getString("TUTOR_NICK");
		   }
		   else
		   {
			   nickname = null;
		   }		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return nickname;
   }
   
}