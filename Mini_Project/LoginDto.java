package Mini_Project;

public class LoginDto {
	private String memPk;
	private String istutor;

	
	public String getMemPk() {
		return memPk;
	}
	public void setMemPk(String memPk) {
		this.memPk = memPk;
	}
	public String getIstutor() {
		return istutor;
	}
	public void setIstutor(String istutor) {
		this.istutor = istutor;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [memPk=" + memPk + ", istutor=" + istutor + "]";
	}
	
}
