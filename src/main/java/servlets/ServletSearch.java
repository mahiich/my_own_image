package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String searchValue = request.getParameter("seaval");

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
            connection = DriverManager.getConnection(dburl, "postgres", "admin");

            String sql = "select * from students";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CharSequence search = searchValue;
                if (resultSet.getString("name").contains(search)){
                    printWriter.println("<html>");
                    printWriter.println("<h3>"+resultSet.getString("name")+"</h3>");
                    printWriter.println("</html>");
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
