package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletDeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        short id= Short.parseShort(request.getParameter("id"));

        PrintWriter printWriter = response.getWriter();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
            connection = DriverManager.getConnection(dburl, "postgres", "admin");

            String sql = "delete from students where id=?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setShort(1,id);
            preparedStatement.executeUpdate();


            response.sendRedirect("/getAdd");
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
