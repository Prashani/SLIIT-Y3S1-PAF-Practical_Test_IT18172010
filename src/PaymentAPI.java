
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PaymentAPI() {
		// TODO Auto-generated constructor stub
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Payment paymentObj = new Payment();
		String output = paymentObj.readPayments2();
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Payment paymentObj = new Payment();
		String output = paymentObj.insertPayment(Integer.parseInt(request.getParameter("txtDoctor")),
				Integer.parseInt(request.getParameter("txtHospital")),
				Integer.parseInt(request.getParameter("txtPatient")), request.getParameter("txtTotal"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Payment paymentObj = new Payment();
		Map paras = getParasMap(request);

		String output = paymentObj.updatePayment(Integer.parseInt(paras.get("txtDoctor").toString()),
				Integer.parseInt(paras.get("txtHospital").toString()),
				Integer.parseInt(paras.get("txtPatient").toString()), paras.get("txtTotal").toString(),
				Integer.parseInt(paras.get("hidItemIDSave").toString()));
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Payment paymentObj = new Payment();
		Map paras = getParasMap(request);
		String output = paymentObj.deleteItem(paras.get("paymentID").toString());
		response.getWriter().write(output);
	}

}
