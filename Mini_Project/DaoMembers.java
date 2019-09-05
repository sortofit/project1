package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoMembers 
{
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;

	public DaoMembers(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}
   
   public MemDto sltOne (String email)        //email를 통한 단거 조회
   {
	  MemDto dtoDB = null;
      
      String sSelect ="SELECT * FROM members WHERE mem_email = ?";
      
      try
      {
         pstmt = con.prepareStatement(sSelect);
         pstmt.setString(1, email);
         rs = pstmt.executeQuery();
         
         if( rs.next() )
         {  
        	 dtoDB = new MemDto();
        	 dtoDB.setMemPk       (rs.getString("mem_pk"      ));
        	 dtoDB.setEmail       (rs.getString("mem_email"   ));
        	 dtoDB.setPw          (rs.getString("mem_pw"      ));
        	 dtoDB.setTel         (rs.getString("mem_add"     ));
        	 dtoDB.setAdd         (rs.getString("mem_tel"     ));
        	 dtoDB.setIsTutor     (rs.getString("mem_istutor" ));           
         }
         else
         {
        	 dtoDB = null;
         }   
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dtoDB;
   }
   
   public MemDto sltOneEmailPw (MemDto dto)        
   {
	  MemDto dtoDB = null;
	  String emeil = dto.getEmail();
	  String pw    = dto.getPw();
      
      String sSelect ="SELECT * FROM members "
      				 +"WHERE  mem_email = ?  "
      				 +"and    mem_pw    = ?  ";
      
      try
      {
         pstmt = con.prepareStatement(sSelect);
         pstmt.setString(1, emeil);
         pstmt.setString(2, pw);
         rs = pstmt.executeQuery();
         
         if( rs.next() )
         {  
        	 dtoDB = new MemDto();
        	 dtoDB.setMemPk       (rs.getString("mem_pk"      ));
        	 dtoDB.setEmail       (rs.getString("mem_email"   ));
        	 dtoDB.setPw          (rs.getString("mem_pw"      ));
        	 dtoDB.setTel         (rs.getString("mem_add"     ));
        	 dtoDB.setAdd         (rs.getString("mem_tel"     ));
        	 dtoDB.setIsTutor     (rs.getString("mem_istutor" ));           
         }
         else
         {
        	 dtoDB = null;
         }   
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dtoDB;
   }
   
   public MemDto sltOnePk (String memPk)	//마이페이지 ID 디폴트로 불러오기
   {
      String sSelect = "SELECT * FROM MEMBERS WHERE MEM_PK = ?";
      
      MemDto memDto = null;
      
      try
      {
         pstmt = con.prepareStatement(sSelect);
         pstmt.setString(1, memPk);
         rs = pstmt.executeQuery();
         
         if( rs.next() )
         {     
        	memDto = new MemDto();
        	memDto.setMemPk  (rs.getString("mem_pk"	 	 ));
         	memDto.setEmail  (rs.getString("mem_email"	 ));
         	memDto.setPw     (rs.getString("mem_pw"		 ));
         	memDto.setAdd    (rs.getString("mem_add"	 ));
         	memDto.setTel  	 (rs.getString("mem_tel"	 ));
         	memDto.setIsTutor(rs.getString("mem_istutor" ));
         }
         else
         {
        	memDto = null;
         }   
      } catch (Exception e) {
         e.printStackTrace();
      }
      return memDto;
   } 
         
   public int insert(MemDto dto) 
   {
      String sInsert = "INSERT INTO members "
      				 + "(mem_pk, mem_email, mem_pw, mem_add, mem_tel, mem_istutor)"
    		  		 + "VALUES(?,?,?,?,?,?)";
      
      int ret = 0;
      
        String memPk   = dto.getMemPk();
		String email   = dto.getEmail();
		String pw      = dto.getPw();
		String add     = dto.getAdd();
		String tel     = dto.getTel();
		String isTutor = dto.getIsTutor();      
      try 
      {
         pstmt = con.prepareStatement(sInsert);
         pstmt.setString(1, memPk);
         pstmt.setString(2, email);
         pstmt.setString(3, pw);
         if(add!=null)
        	 pstmt.setString(4, add);
         else
        	 pstmt.setNull(4, java.sql.Types.NULL);
         if(tel!=null)
        	 pstmt.setString(5, tel);
         else
        	 pstmt.setNull(5, java.sql.Types.NULL);
         pstmt.setString(6, isTutor);
                     
         ret = pstmt.executeUpdate();
         
      } catch (Exception e) { 
         e.printStackTrace();
      }
      return ret;
   }
      
   public String memMaxPk()	//멤버PK
   {
	  String maxPk = null;
      String maxQuery = "SELECT max(to_number(mem_pk)) from members";       
     
      try
      {
         pstmt = con.prepareStatement(maxQuery);
         rs = pstmt.executeQuery();         
         if( rs.next() )
         {
            maxPk = rs.getString("max(to_number(mem_pk))");        	
         } 
         
      } catch (Exception e) {
         e.printStackTrace();
      }      	   
	   return maxPk;
   }
   
   public int update(MemDto memDto)		//BMJ_회원정보 및 PW 변경
   {
	   int ret = 0;
	   
	   String memPk = memDto.getMemPk();
	   String newPw = memDto.getPw();
	   String add = memDto.getAdd();
	   String tel = memDto.getTel();
	   
	   String sUpdate = "update members          "
			   		  + "set	mem_pw		= ?, "
		   			  + "       mem_add     = ?, "
		   			  + "       mem_tel     = ?  "
		   			  + "where  mem_pk      = ?  ";
		   
	   try
		{
			pstmt = con.prepareStatement(sUpdate);
			pstmt.setString(1, newPw);
			if(add!=null)
	        	 pstmt.setString(2, add);
	        else
	        	 pstmt.setNull(2, java.sql.Types.NULL);
			if(tel!=null)
	        	 pstmt.setString(3, tel);
	        else
	        	 pstmt.setNull(3, java.sql.Types.NULL);
			pstmt.setString(4, memPk);
			
			ret = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return ret;
   }
   
   public int isupdate(String memPk)	//BMJ_isTutor변경
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
   
   
   
   
}