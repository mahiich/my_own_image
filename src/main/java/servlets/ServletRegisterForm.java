package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletRegisterForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies=request.getCookies();
        PrintWriter printWriter = response.getWriter();
        for (Cookie cookie:cookies) {
            Connection connection = null;
            try {
                Class.forName("org.postgresql.Driver");
                String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
                connection = DriverManager.getConnection(dburl, "postgres", "admin");

                String sql = "select * from register";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (cookie.getName().equals(resultSet.getString("login"))
                        &&cookie.getValue().equals("password")){
                    response.sendRedirect("/getAdd");
                }

        } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        printWriter.println("<html>");
        printWriter.println("" +
                "    <div id=\"regform\">\n" +
                "        <form method=\"POST\">\n" + //todo register form method
                "\n" +
                "            <label for=\"flogin\">Login:</label><br>\n" +
                "            <input type=\"text\" id=\"flogin\" required name=\"login\" value=\"\"><br>\n" +
                "\n" +
                "            <label for=\"fpassword\">Password:</label><br>\n" +
                "            <input type=\"email\" id=\"fpassword\" required name=\"Password\" value=\"\"><br>\n" +
                "\n" +
                "            <input id=\"endterbutton\" type=\"submit\" value=\"Enter\">\n" +
                "        </form>\n" +
                "\n" +
                "    </div>");
        printWriter.println("");
        printWriter.println("");
        printWriter.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
