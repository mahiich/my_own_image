package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletGetAddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie logincookie = new Cookie("login","mahiich@mail.ru");
        Cookie passwordcookie = new Cookie("password","Password");
        logincookie.setSecure(false);
        passwordcookie.setSecure(false);
        logincookie.setMaxAge(300);
        passwordcookie.setMaxAge(300);
        response.addCookie(logincookie);
        response.addCookie(passwordcookie);

        Short page= Short.parseShort(request.getParameter("page"));

        PrintWriter printWriter = response.getWriter();
        Connection connection = null;
        System.out.println("Cookie secured:"+logincookie.getValue());
        System.out.println("Cookie secured:"+passwordcookie.getValue());
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
            printWriter.println("    " +
                    "    <div id=\"searchdiv\">\n" +
                    "        <form method=\"GET\" action=\"/search\">\n" +
                    "            <input type=\"text\" id=\"fsearch\" required name=\"seaval\" value=\"\">\n" +
                    "            <input id=\"endterbutton\" style=\"color: darkgreen;\" type=\"submit\" value=\"Search\">\n" +
                    "        </form>\n" +
                    "    </div>");
            printWriter.println("<a href=\"/editSave?id=new\" id=\"addbutton\" >Add student</a>");
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
                printWriter.println("<td>"+resultSet.getString("name")+"</td>");/* Pagination links */
                printWriter.println("<td>"+resultSet.getString("email")+"</td>");
                printWriter.println("<td>"+resultSet.getString("course")+"</td>");
                printWriter.println("<td>"+resultSet.getString("age")+"</td>");
                printWriter.println("<td><a id=\"editbutton\" href=\"/editSave?id="+resultSet.getShort("id")+"\">Edit</a></td>");
                printWriter.println("<td><a id=\"deletebutton\"href=\"/delete?id="+resultSet.getShort("id")+"\">Delete</a></td>");

                tablecounter++;
                printWriter.println("</tr>");
            }


            printWriter.println("</table>");
            printWriter.println(" " +
                    "<div class=\"centered\">\n" +
                    "        <div class=\"pagination\">\n" +
                    "            <a href=\"#\">&laquo;</a>\n" +
                    "            <a href=\"#\">1</a>\n" +
                    "            <a class=\"active\" href=\"#\">2</a>\n" +
                    "            <a href=\"#\">3</a>\n" +
                    "            <a href=\"#\">4</a>\n" +
                    "            <a href=\"#\">5</a>\n" +
                    "            <a href=\"#\">6</a>\n" +
                    "            <a href=\"#\">&raquo;</a>\n" +
                    "        </div>\n" +
                    "    </div>");






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
                    "        /* Pagination links */\n" +
                    "        \n" +
                    "        .centered {\n" +
                    "            text-align: center;\n" +
                    "            margin: 20px 38% auto;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .pagination a {\n" +
                    "            color: black;\n" +
                    "            float: left;\n" +
                    "            padding: 8px 16px;\n" +
                    "            text-decoration: none;\n" +
                    "            transition: background-color .3s;\n" +
                    "            border: 1px solid #ddd;\n" +
                    "            border-radius: 5px;\n" +
                    "            /* Gray */\n" +
                    "        }\n" +
                    "        /* Style the active/current link */\n" +
                    "        \n" +
                    "        .pagination a.active {\n" +
                    "            background-color: dodgerblue;\n" +
                    "            color: white;\n" +
                    "            border-radius: 5px;\n" +
                    "        }\n" +
                    "        /* Add a grey background color on mouse-over */\n" +
                    "        \n" +
                    "        .pagination a:hover:not(.active) {\n" +
                    "            background-color: #ddd;\n" +
                    "        }"+
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
