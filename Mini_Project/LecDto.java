package Mini_Project;

public class LecDto {
	private String lecPk;
	private String tutorPk;
	private String title;
	private String ctgr;
	private String crcl;
	private String dir ;
		
	public String getTutorPk() {
		return tutorPk;
	}
	public void setTutorPk(String tutorPk) {
		this.tutorPk = tutorPk;
	}
	public String getLecPk() {
		return lecPk;
	}
	public void setLecPk(String lecPk) {
		this.lecPk = lecPk;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCtgr() {
		return ctgr;
	}
	public void setCtgr(String ctgr) {
		this.ctgr = ctgr;
	}
	public String getCrcl() {
		return crcl;
	}
	public void setCrcl(String crcl) {
		this.crcl = crcl;
	}
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public String toString()
	{
		return "LecDto [lecPk=" + lecPk + "tutorPk=" + tutorPk + "title=" + title + ", ctgr=" + ctgr + ", crcl=" + crcl + ", dir=" + dir + "]";
		
	}
}
