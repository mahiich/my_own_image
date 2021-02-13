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
            printWriter.println("<a href=\"/editSave\" id=\"addbutton\" >Add student</a>");
            printWriter.println("<table style=\"border:1px solid black\" id=\"table\">");
            printWriter.println("<caption>This is table caption</caption>");
            printWriter.println(
                    "  <tr style=\"border:1px solid black\">\n" +
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
                printWriter.println("<tr style=\"border:1px solid black\">");

                printWriter.println("<td>"+tablecounter+"</td>");
                printWriter.println("<td>"+resultSet.getShort("id")+"</td>");
                printWriter.println("<td>"+resultSet.getString("name")+"</td>");
                printWriter.println("<td>"+resultSet.getString("email")+"</td>");
                printWriter.println("<td>"+resultSet.getString("course")+"</td>");
                printWriter.println("<td>"+resultSet.getString("age")+"</td>");
                printWriter.println("<td><a id=\"editbutton\" href=\"/editSave?id="+resultSet.getShort("id")+"\">Edit</a></td>");
                printWriter.println("<td><a id=\"deletebutton\"href=\"/delete?id="+resultSet.getShort("id")+"\">Delete</a></td>");

                tablecounter++;
                printWriter.println("</tr>");
            }


            printWriter.println("</table>");







            printWriter.println("<style>" +        //************  CSS styles
                    "#table tr:nth-child(even) {\n" +
                    "  background-color: #eee;\n" +
                    "}\n" +
                    "#table tr:nth-child(odd) {\n" +
                    "  background-color: #fff;\n" +
                    "}\n" +
                    "#table th {\n" +
                    "  color: white;\n" +
                    "  background-color: black;\n" +
                    "  background-color: black;\n" +
                    "}" +

                    "#addbutton:link {\n" +
                    "  color: white;\n" +
                    "  text-color:yellow;\n" +
                    "  background-color: green;\n" +
                    "  text-decoration: none;\n" +
                    "  border:1px solid red;\n" +
                    "}\n" +
                    "#addbutton:visited {\n" +
                    "  color: pink;\n" +
                    "  background-color: transparent;\n" +
                    "  text-decoration: none;\n" +
                    "}\n" +
                    "#addbutton:hover {\n" +
                    "  color: red;\n" +
                    "  background-color: transparent;\n" +
                    "  text-decoration: underline;\n" +
                    "}\n" +
                    "#addbutton:active {\n" +
                    "  color: yellow;\n" +
                    "  background-color: transparent;\n" +
                    "  text-decoration: underline;\n" +
                    "}" +

                    "#editbutton{" +
                    "color:black;\n" +
                    "background-color:yellow;\n" +
                    "text-decoration: underline;\n" +
                    "}"+

                    "#deletebutton{" +
                    "color:white;\n" +
                    "background-color:red;\n" +
                    "text-decoration: underline;\n" +
                    "}"+

                    "</style>");

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
