
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;

public class Payment {

//	PaymentModel paymentmodel;

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sliitpafs", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(int doctorID, int hospitalID, int patientID, String total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into payments (`patientID`, `hospitalID`,`doctorID`,`total`, `date` )"
					+ " values (?, ?, ?, ?, ?)";

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			System.out.println(formatter.format(date));

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, patientID);
			preparedStmt.setInt(2, hospitalID);
			preparedStmt.setInt(3, doctorID);
			preparedStmt.setString(4, total);
			preparedStmt.setString(5, (formatter.format(date)));

			preparedStmt.execute();
			con.close();

			String newItems = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String insertPaymentDetails(String cardNo, String cvv, String patientID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into paymentDetails (`cardNo`, `cvv`,`patientID` )" + " values (?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, cardNo);
			preparedStmt.setString(2, cvv);
			preparedStmt.setString(3, patientID);

			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String readPayments() {
		List<PaymentModel> payementesList = new ArrayList<>();
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
//				return "Error while connecting to the database for reading.";
			}

			output = "<table class='table table-striped' border='1'><thead style='text-align:center;margin-bottom:120px' class='thead-dark'><tr><th>Payment ID</th> <th>Hospital ID</th><th>Patient ID</th>"
					+ "<th>Doctor ID</th> <th>Total</th><th>Dates</th><th>Update</th><th>Remove</th></tr></thead>";

			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("id"));
				String patientID = Integer.toString(rs.getInt("patientID"));
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String doctorID = Integer.toString(rs.getInt("doctorID"));
				String total = rs.getString("total");
				String dates = rs.getString("date");

				output += "<tr style='text-align:center'><td>" + paymentID + "</td>";
				output += "<td>" + hospitalID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + total + "</td>";
				output += "<td>" + dates + "</td>";

				output += "<td><input id='btnUpdate' name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemID='"+ paymentID + "'></td> <td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ paymentID + "'>" + "</td></tr>";

				System.out.println("lol" + paymentID + patientID);

			}

		} catch (Exception e) {

			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		System.out.println(output);
		return output;
	}
	
	public String readPayments2() {
		
		String output = "";
		

			String newItems = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		
		return output;
	}
	
	public String updatePayment(int doctorID, int hospitalID, int patientID, String total,int paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "UPDATE payments SET patientID= ? ,hospitalID=?,doctorID=?,total=?,date=? WHERE id=?";

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			System.out.println(formatter.format(date));

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, patientID);
			preparedStmt.setInt(2, hospitalID);
			preparedStmt.setInt(3, doctorID);
			preparedStmt.setString(4, total);
			preparedStmt.setString(5, (formatter.format(date)));
			preparedStmt.setInt(6, paymentID);

			preparedStmt.execute();
			con.close();

			String newItems = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public List<PaymentModel> readPaymentsUser(String id, String user) {
		String userType = "";
		List<PaymentModel> payementesList = new ArrayList<>();
		switch (user) {
		case "patient":
			userType = "patientID";
			break;
		case "hospital":
			userType = "hospitalID";
			break;
		case "doctor":
			userType = "doctorID";
			break;

		default:
			break;
		}

		try {
			Connection con = connect();
			if (con == null) {
//				return "Error while connecting to the database for reading.";
			}

			String query = "select * from payments where " + userType + " = " + id;

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("id"));
				String patientID = Integer.toString(rs.getInt("patientID"));
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String doctorID = Integer.toString(rs.getInt("doctorID"));
				String total = rs.getString("total");
				String dates = rs.getString("date");

				PaymentModel paymodel = new PaymentModel();
				paymodel.setDates(dates);
				paymodel.setDoctorID(doctorID);
				paymodel.setHospitalID(hospitalID);
				paymodel.setPatientID(patientID);
				paymodel.setPaymentID(paymentID);
				paymodel.setTotal(total);

				payementesList.add(paymodel);

			}

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
		return payementesList;
	}

	public String deleteItem(String paymentID) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			String query = "delete from payments where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			preparedStmt.execute();
			con.close();
			String newItems = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePaymentDetails(String cardNo, String cvv, String patientID, int ppymentDetailsId) {

		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE paymentDetails SET cardNo= ? ,cvv=?,patientID=? WHERE idpaymentDetails=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, cardNo);
			preparedStmt.setString(2, cvv);
			preparedStmt.setString(3, patientID);
			preparedStmt.setInt(4, ppymentDetailsId);

			preparedStmt.execute();

			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public PaymentDetailsModel readPaymentsDetailsUser(String patientsID) {
		PaymentDetailsModel details = new PaymentDetailsModel();

		try {
			Connection con = connect();
			if (con == null) {

//				return "Error while connecting to the database for reading.";
			}

			String query = "select * from paymentDetails where patientID = " + patientsID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String cvv = rs.getString("cvv");
				String cardNo = rs.getString("cardNo");
				int idpaymentDetails = rs.getInt("idpaymentDetails");
				// String idpaymentDetails = "sfdf";
				String patientID = rs.getString("patientID");

				details.setCvv(cvv);
				details.setCardNo(cardNo);
				details.setPatientID(patientID);
				details.setPpymentDetailsId(idpaymentDetails);

			}

		} catch (Exception e) {
			System.err.println(e.getMessage() + "---------------------------------------------");
		}

		return details;
	}

}
