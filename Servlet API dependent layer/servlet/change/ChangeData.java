package servlet.change;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;
import forum.data.objects.Profile;

/**
 * Servlet implementation class ChangeData
 */
@WebServlet("/ChangeData")
public class ChangeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void error(String word, HttpServletResponse response){
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Error</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Can't change your " + word + "</p>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User us = (User) request.getSession().getAttribute("user");
		Admin ad = (Admin) request.getSession().getAttribute("admin");
		String pass = request.getParameter("pass");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		String birthdate = "";
		String month = request.getParameter("monthdropdown");
		String day = request.getParameter("daydropdown");
		if(!request.getParameter("yeardropdown").equals("") && !month.equals("") && !day.equals("")){
			if(month.length() == 1)
				month = "0" + month;
			if(day.length() == 1)
				day = "0" + day;
			birthdate = request.getParameter("yeardropdown") + month + day;
		}
		String email = request.getParameter("email");
		String avatar = request.getParameter("avatar");
		String signature = request.getParameter("signature");
		if (us != null) {
			if (!pass.equals("")) {
				try {
					if (us.modifyPassword(pass)) {
						us.getProfile().SetPassword(pass);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("password", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!firstname.equals("")) {
				try {
					if (us.ModifyFirstname(firstname)) {
						us.getProfile().SetFirstName(firstname);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("firstName", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!lastname.equals("")) {
				try {
					if (us.ModifyLastname(lastname)) {
						us.getProfile().SetLastName(lastname);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("lastName", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!gender.equals("")) {
				try {
					if (us.ModifyGender(gender.charAt(0) + "")) {
						us.getProfile().SetGender(gender.charAt(0) + "");
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("gender", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!birthdate.equals("")) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			        java.util.Date parsed = format.parse(birthdate);
			        java.sql.Date sql = new java.sql.Date(parsed.getTime());
					if (us.ModifyBirthdate(sql)) {
						us.getProfile().SetBirthDate(sql);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("birthdate", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (!email.equals("")) {
				try {
					if (us.ModifyEmail(email)) {
						us.getProfile().SetEmail(email);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("email", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!avatar.equals("")) {
				try {
					if (us.ModifyAvatar(avatar)) {
						us.getProfile().SetAvatar(avatar);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("avatar", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!signature.equals("")) {
				try {
					if (us.ModifySignature(signature)) {
						us.getProfile().SetSignature(signature);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("signature", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (ad != null) {
			if (!pass.equals("")) {
				try {
					if (ad.modifyPassword(pass)) {
						ad.getProfile().SetPassword(pass);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("password", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!firstname.equals("")) {
				try {
					if (ad.ModifyFirstname(firstname)) {
						ad.getProfile().SetFirstName(firstname);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("firstName", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!lastname.equals("")) {
				try {
					if (ad.ModifyLastname(lastname)) {
						ad.getProfile().SetLastName(lastname);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("lastName", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!gender.equals("")) {
				try {
					if (ad.ModifyGender(gender.charAt(0) + "")) {
						ad.getProfile().SetGender(gender.charAt(0) + "");
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("gender", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!birthdate.equals("")) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			        java.util.Date parsed = format.parse(birthdate);
			        java.sql.Date sql = new java.sql.Date(parsed.getTime());
					if (ad.ModifyBirthdate(sql)) {
						ad.getProfile().SetBirthDate(sql);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("birthdate", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (!email.equals("")) {
				try {
					if (ad.ModifyEmail(email)) {
						ad.getProfile().SetEmail(email);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("email", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!avatar.equals("")) {
				try {
					if (ad.ModifyAvatar(avatar)) {
						ad.getProfile().SetAvatar(avatar);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("avatar", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!signature.equals("")) {
				try {
					if (ad.ModifySignature(signature)) {
						ad.getProfile().SetSignature(signature);
						request.getRequestDispatcher(
								"profile.jsp?id=" + ad.userID).forward(request,
								response);
					} else {
						error("signature", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
