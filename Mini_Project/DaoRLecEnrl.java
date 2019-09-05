package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DaoRLecEnrl 
{
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
   
   public DaoRLecEnrl(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}
   
   public ArrayList<LecListDto> entire(int startrow, int rowlimit)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   						 "
       +"FROM   ( SELECT O.*                                     "
       +"              , ROWNUM RNUM                             "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name "
       +"                from   lecture l, category c, tutor t	 "
       +"				 where    t.tutor_pk = l.tutor_pk		 "
       +"                and    c.ctgry_pk = l.ctgry_pk          "
       +"              ) O                                       "
       +"       )                                                "
       +"WHERE  1=1                                              "
       +"AND    RNUM    >= ?                                     "
       +"AND    ROWNUM  <= ?                                     ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setInt(1, startrow);
          pstmt.setInt(2, rowlimit);
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
       
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
   
   public ArrayList<LecListDto> title(int startRow, int rowLimit, String sCondition)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   						 "
       +"FROM   ( SELECT O.*                                     "
       +"              , ROWNUM RNUM                             "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name "
       +"                from   lecture l, category c, tutor t   "
       +"				 where    t.tutor_pk = l.tutor_pk		 "
       +"                and    c.ctgry_pk = l.ctgry_pk          "
       +"                and    l.lec_title like ?               "
       +"              ) O                                       "
       +"       )                                                "
       +"WHERE  1=1                                              "
       +"AND    RNUM    >= ?                                     "
       +"AND    ROWNUM  <= ?                                     ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setString(1, "%"+sCondition+"%");
          pstmt.setInt(2, startRow);
          pstmt.setInt(3, rowLimit);
          
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
       
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
   
   public ArrayList<LecListDto> tutor(int startRow, int rowLimit, String sCondition)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   						 "
       +"FROM   ( SELECT O.*                                     "
       +"              , ROWNUM RNUM                             "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name "
       +"                from   lecture l, category c, tutor t   "
       +"				 where  t.tutor_pk = l.tutor_pk			 "
       +"                and    c.ctgry_pk = l.ctgry_pk          "
       +"                and    t.tutor_Nick like ?              "
       +"              ) O                                       "
       +"       )                                                "
       +"WHERE  1=1                                              "
       +"AND    RNUM    >= ?                                     "
       +"AND    ROWNUM  <= ?                                     ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setString(1, "%"+sCondition+"%");
          pstmt.setInt(2, startRow);
          pstmt.setInt(3, rowLimit);
          
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
       
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
   
   public ArrayList<LecListDto> ctgry(int startRow, int rowLimit, int ctgryPk)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   						 "
       +"FROM   ( SELECT O.*                                     "
       +"              , ROWNUM RNUM                             "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name "
       +"                from   lecture l, category c, tutor t   "
       +"				 where  t.tutor_pk = l.tutor_pk			 "
       +"                and    c.ctgry_pk = l.ctgry_pk          "
       +"                and    c.ctgry_pk = ?                   "
       +"              ) O                                       "
       +"       )                                                "
       +"WHERE  1=1                                              "
       +"AND    RNUM    >= ?                                     "
       +"AND    ROWNUM  <= ?                                     ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setInt(1, ctgryPk);
          pstmt.setInt(2, startRow);
          pstmt.setInt(3, rowLimit);
          
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
         	 
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
}