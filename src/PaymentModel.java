

public class PaymentModel {

	private String paymentID;
	private String patientID;
	private String hospitalID;
	private String doctorID;
	private String total;
	private String dates;
	
	public PaymentModel() {
		
	}
	
	public PaymentModel(String paymentID, String patientID, String hospitalID, String doctorID, String total,
			String dates) {
		super();
		this.paymentID = paymentID;
		this.patientID = patientID;
		this.hospitalID = hospitalID;
		this.doctorID = doctorID;
		this.total = total;
		this.dates = dates;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}
	
	
	
	
}


