package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletEditSaveStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id= request.getParameter("id");

        PrintWriter printWriter = response.getWriter();
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
            connection = DriverManager.getConnection(dburl, "postgres", "admin");

            if (id!=null) {
                String sql = "select * from students where id=?";//todo
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                preparedStatement.setShort(1, Short.parseShort(id));
                ResultSet resultSet = preparedStatement.executeQuery();


                printWriter.println("<html>");

                printWriter.println("    <div id=\"formdiv\">\n" +
                        "        <form method=\"POST\">\n" +

                        "            <label for=\"fname\">Name:</label><br>\n" +
                        "            <input type=\"text\" id=\"fname\" required name=\"name\" value=\""+resultSet.getString("name")+"\"><br>\n" +
                        "\n" +
                        "            <label for=\"femail\">Email:</label><br>\n" +
                        "            <input type=\"email\" id=\"femail\" required name=\"email\" value=\""+resultSet.getString("email")+"\"><br>\n" +
                        "\n" +
                        "            <label for=\"fcourse\">Course:</label><br>\n" +
                        "            <input type=\"number\" id=\"fcourse\" required name=\"course\" value=\""+resultSet.getShort("course")+"\"><br>\n" +
                        "\n" +
                        "            <label for=\"fage\">Age:</label><br>\n" +
                        "            <input type=\"number\" id=\"fage\" required name=\"age\" value=\""+resultSet.getShort("age")+"\"><br>\n" +
                        "\n" +
                        "            <input id=\"submitbutton\" type=\"submit\" value=\"Save\">\n" +
                        "        </form>\n" +
                        "\n" +
                        "    </div>\n" +
                        "\n");
                printWriter.println("<style>\n" +
                        "        label {\n" +
                        "            color: red;\n" +
                        "            width: 30px;\n" +
                        "        }\n" +
                        "        \n" +
                        "        #submitbutton {\n" +
                        "            margin: 10px 30px auto;\n" +
                        "            width: 100px;\n" +
                        "            color: rgb(219, 81, 17);\n" +
                        "            background-color: cornsilk;\n" +
                        "        }\n" +
                        "        \n" +
                        "        #formdiv {\n" +
                        "            padding: 2rem;\n" +
                        "            width: 172px;\n" +
                        "            border: 3px solid salmon;\n" +
                        "            margin: 14% 42% auto;\n" +
                        "        }\n" +
                        "    </style>");

                printWriter.println("</html>");



            }
            else if (id==null){
                printWriter.println("<html>");

                printWriter.println("    <div id=\"formdiv\">\n" +
                        "        <form method=\"POST\">\n" +

                        "            <label for=\"fname\">Name:</label><br>\n" +
                        "            <input type=\"text\" id=\"fname\" required name=\"name\" value=\"\"><br>\n" +
                        "\n" +
                        "            <label for=\"femail\">Email:</label><br>\n" +
                        "            <input type=\"email\" id=\"femail\" required name=\"email\" value=\"\"><br>\n" +
                        "\n" +
                        "            <label for=\"fcourse\">Course:</label><br>\n" +
                        "            <input type=\"number\" id=\"fcourse\" required name=\"course\" value=\"\"><br>\n" +
                        "\n" +
                        "            <label for=\"fage\">Age:</label><br>\n" +
                        "            <input type=\"number\" id=\"fage\" required name=\"age\" value=\"\"><br>\n" +
                        "\n" +
                        "            <input id=\"submitbutton\" type=\"submit\" value=\"Save\">\n" +
                        "        </form>\n" +
                        "\n" +
                        "    </div>\n" +
                        "\n");
                    printWriter.println("<style>\n" +
                            "        label {\n" +
                            "            color: red;\n" +
                            "            width: 30px;\n" +
                            "        }\n" +
                            "        \n" +
                            "        #submitbutton {\n" +
                            "            margin: 10px 30px auto;\n" +
                            "            width: 100px;\n" +
                            "            color: rgb(219, 81, 17);\n" +
                            "            background-color: cornsilk;\n" +
                            "        }\n" +
                            "        \n" +
                            "        #formdiv {\n" +
                            "            padding: 2rem;\n" +
                            "            width: 172px;\n" +
                            "            border: 3px solid salmon;\n" +
                            "            margin: 14% 42% auto;\n" +
                            "        }\n" +
                            "    </style>");

                printWriter.println("</html>");
            }






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
