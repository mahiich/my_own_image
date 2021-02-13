package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletGetAddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String s=request.getParameter("name");

        PrintWriter printWriter = response.getWriter();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
            connection = DriverManager.getConnection(dburl, "postgres", "admin");

            String sql = "select * from students";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            int tablecounter=1;
            printWriter.println("<html>");
            printWriter.println("<h1 style=\"text-align:center\";>Students list</h1>");
            printWriter.println("<table border:2px>");
            printWriter.println(
                    "  <tr>\n" +
                    "    <th>#</th>\n" +
                    "    <th>id</th>\n" +
                    "    <th>name</th>\n" +
                    "    <th>email</th>\n" +
                    "    <th>course</th>\n" +
                    "    <th>age</th>\n" +
                    "    <th>edit</th>\n" +
                    "    <th>delete</th>\n" +
                    "  </tr>");

            while (resultSet.next()){
                printWriter.println("<tr>");
                printWriter.println("<td>");





                printWriter.println("</tr>");
            }


            printWriter.println("</table>");









            printWriter.println("<html>");




            preparedStatement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
