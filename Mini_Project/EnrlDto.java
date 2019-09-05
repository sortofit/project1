package Mini_Project;

public class EnrlDto {
	
	private String lecPk;
	private String memPk;
	
	public String getLecPk() {
		return lecPk;
	}
	public void setLecPk(String lecPk) {
		this.lecPk = lecPk;
	}
	public String getMemPk() {
		return memPk;
	}
	public void setMemPk(String memPk) {
		this.memPk = memPk;
	}
	@Override
	public String toString() {
		return "EnrlDto [lecPk=" + lecPk + ", memPk=" + memPk + "]";
	}
	
	
	

}
