package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletEditSaveStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String s=request.getParameter("name");

        PrintWriter printWriter = response.getWriter();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
            connection = DriverManager.getConnection(dburl, "postgres", "admin");

            String sql = "select * from students";//todo
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();







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
