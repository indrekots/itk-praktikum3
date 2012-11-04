package ee.itcollege.i377.praks3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//pastebin link http://pastebin.com/FggkvRq0

//GuardDAO - http://pastebin.com/tv7UjMWh
//guard.jsp - http://pastebin.com/eP0GwSvL
//Guard.java - http://pastebin.com/3Hj2s7it


@WebServlet(name = "DatabaseInit", urlPatterns = { "/DatabaseInit" })
public class SetupDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:/home/indrek/hsqldb/db", "sa", "");
			PrintWriter out = response.getWriter();
			out.write(conn.getMetaData().getDatabaseProductName());
			
			Statement statement = conn.createStatement();
			
			StringBuilder sb = new StringBuilder();
			sb.append("create table guard ( ");
			sb.append("		id IDENTITY, ");
			sb.append("		name VARCHAR(50), ");
			sb.append("		age INT ");
			sb.append(") ");
			statement.execute(sb.toString());
			
			statement.execute("insert into guard values (1, 'Indrek', 32)");
			statement.execute("insert into guard values (2, 'Hindrek', 31)");
			statement.execute("insert into guard values (3, 'Hendrik', 36)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
