package Mini_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DaoRMyClass 
{
	private Connection 			 con    = null;
	private PreparedStatement    pstmt  = null; 
	private ResultSet            rs     = null;
   
   public DaoRMyClass(Connection con, PreparedStatement pstmt, ResultSet rs) 
	{
		this.con = con;
		this.pstmt = pstmt;
		this.rs = rs;
	}
   
   public ArrayList<LecListDto> entire(String memPk, int startrow, int rowLimit)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   										   "
       +"FROM   ( SELECT O.*                                                       "
       +"              , ROWNUM RNUM                                               "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name	               "
       +"                from   members m, enroll e, lecture l, category c, tutor t"
       +"                where  m.mem_pk = e.mem_pk                                "
       +"                and    e.lec_pk = l.lec_pk                                "
       +"				 and    t.tutor_pk = l.tutor_pk							   "
       +"                and    c.ctgry_pk = l.ctgry_pk                            "
       +"                and    m.mem_pk = ?                                       "
       +"              ) O                                                         "
       +"       )                                                                  "
       +"WHERE  1=1                                                                "
       +"AND    RNUM    >= ?                                                       "
       +"AND    ROWNUM  <= ?                                                       ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setString(1, memPk);
          pstmt.setInt(2, startrow);
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
         	 dto.setDir(rs.getString("lec_dir"));
       
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
   
   public ArrayList<LecListDto> title(String memPk, int startRow, int rowLimit, String sCondition)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
	   LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   										   "
       +"FROM   ( SELECT O.*                                                       "
       +"              , ROWNUM RNUM                                               "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name	               "
       +"                from   members m, enroll e, lecture l, category c, tutor t"
       +"                where  m.mem_pk = e.mem_pk                                "
       +"                and    e.lec_pk = l.lec_pk                                "
       +"				 and    t.tutor_pk = l.tutor_pk							   "
       +"                and    c.ctgry_pk = l.ctgry_pk                            "
       +"                and    m.mem_pk = ?                                       "
       +"                and    l.lec_title like ?                                       "
       +"              ) O                                                         "
       +"       )                                                                  "
       +"WHERE  1=1                                                                "
       +"AND    RNUM    >= ?                                                       "
       +"AND    ROWNUM  <= ?                                                       ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setString(1, memPk);
          pstmt.setString(2, "%"+sCondition+"%");
          pstmt.setInt(3, startRow);
          pstmt.setInt(4, rowLimit);
          
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
         	 dto.setDir(rs.getString("lec_dir"));
       
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
   
   public ArrayList<LecListDto> tutor(String memPk, int startRow, int rowLimit, String sCondition)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   										   "
       +"FROM   ( SELECT O.*                                                       "
       +"              , ROWNUM RNUM                                               "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name	               "
       +"                from   members m, enroll e, lecture l, category c, tutor t"
       +"                where  m.mem_pk = e.mem_pk                                "
       +"                and    e.lec_pk = l.lec_pk                                "
       +"				 and    t.tutor_pk = l.tutor_pk							   "
       +"                and    c.ctgry_pk = l.ctgry_pk                            "
       +"                and    m.mem_pk = ?                                       "
       +"                and    t.tutor_Nick like ?                                       "
       +"              ) O                                                         "
       +"       )                                                                  "
       +"WHERE  1=1                                                                "
       +"AND    RNUM    >= ?                                                       "
       +"AND    ROWNUM  <= ?                                                       ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setString(1, memPk);
          pstmt.setString(2, "%"+sCondition+"%");
          pstmt.setInt(3, startRow);
          pstmt.setInt(4, rowLimit);
          
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
         	 dto.setDir(rs.getString("lec_dir"));
       
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
   
   public ArrayList<LecListDto> ctgry(String memPk, int startRow, int rowLimit, int ctgryPk)
   {
	   ArrayList<LecListDto> arrL = new ArrayList<LecListDto>();
       LecListDto dto;
       
       String sSelect 
       ="SELECT     *                   										   "
       +"FROM   ( SELECT O.*                                                       "
       +"              , ROWNUM RNUM                                               "
       +"         FROM ( select l.* , t.tutor_nick, c.ctgry_name	               "
       +"                from   members m, enroll e, lecture l, category c, tutor t"
       +"                where  m.mem_pk = e.mem_pk                                "
       +"                and    e.lec_pk = l.lec_pk                                "
       +"				 and    t.tutor_pk = l.tutor_pk							   "
       +"                and    c.ctgry_pk = l.ctgry_pk                            "
       +"                and    m.mem_pk = ?                                       "
       +"                and    c.ctgry_pk = ?                                       "
       +"              ) O                                                         "
       +"       )                                                                  "
       +"WHERE  1=1                                                                "
       +"AND    RNUM    >= ?                                                       "
       +"AND    ROWNUM  <= ?                                                       ";
       
       try
       {
          pstmt = con.prepareStatement(sSelect);
          pstmt.setString(1, memPk);
          pstmt.setInt(2, ctgryPk);
          pstmt.setInt(3, startRow);
          pstmt.setInt(4, rowLimit);
          
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
         	 dto = new LecListDto();
         	 dto.setLecPk(rs.getString("lec_pk"));
         	 dto.setTutorNick(rs.getString("tutor_nick"));
         	 dto.setTitle(rs.getString("lec_title"));
         	 dto.setCrcl(rs.getString("lec_crcl"));
         	 dto.setCtgryName(rs.getString("ctgry_name"));
         	 dto.setDir(rs.getString("lec_dir"));
         	 
             arrL.add(dto);
          }
          
       } catch (Exception e) {
          e.printStackTrace();
       }      
       
       return arrL;
   }
}