



public class Client {
	private String fname;
	private String lname;
	private String studentNum;
	private String license;
	private String pin;
	private String status;
	private String carModel;
	private String carColour;
	private String email;
	private String insuranceComp;
	private int policyNum;

	public Client(String studentNum, String pin, String lname, String fname, String status) {
		this.fname = fname;
		this.lname = lname;
		this.studentNum = studentNum;
		this.pin = pin;
		this.status = status;
	}

	/* get methods */
	public String getFName() {
		return fname;
	}
	
	public String getLName(){
		return lname;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public String getLicense() {
		return license;
	}

	public String getPin() {
		return pin;
	}

	public String getStatus() {
		return status;
	}

	public String getCarModel() {
		return carModel;
	}

	public String getCarColour() {
		return carColour;
	}

	public String getEmail() {
		return email;
	}

	public String getInsuranceComp() {
		return insuranceComp;
	}

	/* set methods */
	public void setStatus(String new_status) {
		status = new_status;
	}

	public void setEmail(String new_email) {
		email = new_email;
	}

	public void setCarColour(String new_colour) {
		carColour = new_colour;
	}

	public void setModel(String new_model) {
		carModel = new_model;
	}

	public void setPolicyNum(int new_policy) {
		policyNum = new_policy;
	}
}
