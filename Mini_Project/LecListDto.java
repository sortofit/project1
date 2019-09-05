package Mini_Project;

public class LecListDto {
	private String lecPk;
	private String title;
	private String tutorNick;
	private String crcl;
	private String ctgryName;
	private String dir;
	
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
	public String getTutorNick() {
		return tutorNick;
	}
	public void setTutorNick(String tutorNick) {
		this.tutorNick = tutorNick;
	}
	public String getCrcl() {
		return crcl;
	}
	public void setCrcl(String crcl) {
		this.crcl = crcl;
	}
	public String getCtgryName() {
		return ctgryName;
	}
	public void setCtgryName(String ctgryName) {
		this.ctgryName = ctgryName;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	@Override
	public String toString() {
		return "LecListDto [lecPk=" + lecPk + ", title=" + title + ", tutorNick=" + tutorNick + ", crcl=" + crcl
				+ ", ctgryName=" + ctgryName + ", dir=" + dir + "]";
	}
	
	
	
	
	

}
