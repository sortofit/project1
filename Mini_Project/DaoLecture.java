package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DaoLecture {
	
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
	   
    public DaoLecture(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}

   public LecDto lecSltOne(LecDto lecDto)	//동일튜터의 동일강좌 중복등록 여부 
   {
	   String sLecSltOne 
	   = " SELECT L.*						"
	   + " 		, C.CTGRY_NAME				"
	   + " FROM   LECTURE  L				"
	   + "		, CATEGORY C				"
	   + " WHERE  L.CTGRY_PK   = C.CTGRY_PK "
	   + " AND    C.CTGRY_NAME = ?			"
	   + " AND    L.TUTOR_PK   = ?			"
	   + " AND    L.LEC_TITLE  = ?			";
	   
	   LecDto sameLecDto = null;
	   
	   String ctgryPk = lecDto.getCtgr();
	   String tutorPk = lecDto.getTutorPk();
	   String title = lecDto.getTitle();
	  
	   try
	   {
		   pstmt = con.prepareStatement(sLecSltOne);
		   pstmt.setString(1, ctgryPk);
		   pstmt.setString(2, tutorPk);
		   pstmt.setString(3, title);
		   rs = pstmt.executeQuery();
		   
		   if( rs.next() )
		   {  
			   sameLecDto = new LecDto();
			   sameLecDto.setLecPk(rs.getString("lec_pk"));
			   sameLecDto.setTutorPk(rs.getString("tutor_pk"));
			   sameLecDto.setTitle(rs.getString("lec_title"));
			   sameLecDto.setCrcl(rs.getString("lec_crcl"));
			   sameLecDto.setDir(rs.getString("lec_dir"));
			   sameLecDto.setCtgr(rs.getString("ctgry_pk"));
		   }
		   else
		   {
			   sameLecDto = null;
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return sameLecDto;
   }
   
   public int lecInsert(LecDto lecDto)	//강좌등록 
   {
	   String lecInsert 
	   = "INSERT INTO LECTURE (LEC_PK, TUTOR_PK, LEC_TITLE, LEC_CRCL, LEC_DIR, CTGRY_PK) "
	   + "			   VALUES (?,?,?,?,?,?)										 		 " ;
	   
	   int ret = 0;
	   
	   String lec_pk 	= lecDto.getLecPk();	//강좌 코드(PK) - 자동부여
	   String tutor_pk 	= lecDto.getTutorPk();	//튜터 코드(FK) - 
	   String lec_title = lecDto.getTitle();	//강좌 타이틀
	   String lec_crcl  = lecDto.getCrcl();		//강좌 커리큘럼
	   String lec_dir 	= lecDto.getDir() ;		//강좌 경로
	   String ctgr_pk   = lecDto.getCtgr();		//카테고리 코드(FK)
	   
	   try
	   {
		   pstmt = con.prepareStatement(lecInsert);
	       pstmt.setString(1, lec_pk);
	       pstmt.setString(2, tutor_pk);
	       pstmt.setString(3, lec_title);
	       pstmt.setString(4, lec_crcl);
	       pstmt.setString(5, lec_dir);
	       pstmt.setString(6, ctgr_pk);
	       
	       ret = pstmt.executeUpdate();
	   } catch (Exception e) {
	       e.printStackTrace();
	   }   
	   return ret;
   }
   
   public int lecCodeMax() 	//강좌PK 부여 
   {
	   String lecCodeSlt = "SELECT MAX(TO_NUMBER(LEC_PK)) FROM LECTURE";
	   
	   int lecCodeMax = 0;
	   
	   try
	   {
		   pstmt = con.prepareStatement(lecCodeSlt);
		   rs = pstmt.executeQuery();
		   
		   if( rs.next() )
		   {
			   lecCodeMax = rs.getInt("MAX(TO_NUMBER(LEC_PK))");
		   }
	   } catch (Exception e) {
	       e.printStackTrace();
	   }   
	   return lecCodeMax;
   }
   
   public String ctgrySlt(String ctgry)	//카테고리PK 가져오기 
   {
	   String ctgrPkSlt = "SELECT CTGRY_PK FROM CATEGORY WHERE CTGRY_NAME = ? ";
	   
	   String ctgryPk = null;
	   
	   try
	   {
		   pstmt = con.prepareStatement(ctgrPkSlt);
		   pstmt.setString(1, ctgry);
		   rs = pstmt.executeQuery();
		   
		   if( rs.next() )
		   {
			   ctgryPk = rs.getString("CTGRY_PK");
		   }
	   } catch (Exception e) {
	       e.printStackTrace();
	   } 
	   return ctgryPk;
   }

}