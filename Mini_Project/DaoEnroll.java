package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoEnroll 
{
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
   
   public DaoEnroll(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}
   
   public EnrlDto sltOne (EnrlDto dto) throws SQLException 
   {
	   EnrlDto dtoDB = null;
	   String memPk = dto.getMemPk();
	   String lecPk = dto.getLecPk();
	   
       String sSelect ="select * from enroll " 
    	 	  		  +"where  mem_pk = ?    "
    		  		  +"and    lec_pk = ?    ";
	      
       pstmt = con.prepareStatement(sSelect);
       pstmt.setString(1, memPk);
       pstmt.setString(2, lecPk);
       rs = pstmt.executeQuery();
       
       if( rs.next() )
       {       	 
      	dtoDB = new EnrlDto();
      	dtoDB.setMemPk(rs.getString("mem_pk"));
      	dtoDB.setLecPk(rs.getString("lec_pk"));
      	
       }
       else
       {
      	 dtoDB = null;
       }     
	      
	   return dtoDB;
   }
   
   public int insert(EnrlDto dto) throws SQLException 
   {
	   int ret = 0;
	   String sInsert ="insert into enroll "
			   		  +"(mem_pk, lec_pk)   "
			   		  +"values (?,?)       ";
	   
	   String memPk = dto.getMemPk();
	   String lecPk = dto.getLecPk();
      
	   pstmt = con.prepareStatement(sInsert);
       pstmt.setString(1, memPk);
       pstmt.setString(2, lecPk);
       ret = pstmt.executeUpdate();
      
       return ret;
   }
}