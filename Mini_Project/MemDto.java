package Mini_Project;

public class MemDto {
	
	private String memPk;
	private String email;
	private String pw;
	private String tel;
	private String add;
	private String isTutor = "N";
	
	public String getMemPk() {
		return memPk;
	}
	public void setMemPk(String memPk) {
		this.memPk = memPk;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getIsTutor() {
		return isTutor;
	}
	public void setIsTutor(String isTutor) {
		this.isTutor = isTutor;
	}
	
	@Override
	public String toString() {
		return "MemDto [memPk=" + memPk + ", email=" + email + ", pw=" + pw + ", tel=" + tel + ", address=" + add
				+ ", isTutor=" + isTutor + "]";
	}
	
	
	
}
