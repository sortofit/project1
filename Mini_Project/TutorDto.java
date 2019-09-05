package Mini_Project;

public class TutorDto 
{
	private String tutorPk ;	//자동부여
	private String nickname ;	//필수입력항목
	private String univ ;
	private String cert ;
	
	public String getTutorPk() {
		return tutorPk;
	}
	public void setTutorPk(String tutorPk) {
		this.tutorPk = tutorPk;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUniv() {
		return univ;
	}
	public void setUniv(String univ) {
		this.univ = univ;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	
	public String toString() {
		return "TutorDto [tutorPk=" + tutorPk + "nickname=" + nickname + ", univ=" + univ + ", cert=" + cert + "]";
		
	}
	
}
