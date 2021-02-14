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
            System.out.println("Cookie: "+cookie);
            Connection connection = null;
            try {
                Class.forName("org.postgresql.Driver");
                String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
                connection = DriverManager.getConnection(dburl, "postgres", "admin");

                String sql = "select * from register";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                boolean a = false;
                
                while (resultSet.next()){
                    if (cookie.getName().equals("login")
                            &&cookie.getValue().equals(resultSet.getString("login"))){
                        a=true;
                    }
                    else if(cookie.getName().equals("password")
                    && cookie.getValue().equals(resultSet.getString("password"))){
                        if (a==true){
                            response.sendRedirect("/getAdd");
                        }
                    }
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
                "        <form method=\"POST\" action=\"/\" >\n" + //todo register form method
                "\n" +
                "            <label for=\"flogin\">Login:</label><br>\n" +
                "            <input type=\"email\" id=\"flogin\" required name=\"login\" value=\"\"><br>\n" +
                "\n" +
                "            <label for=\"fpassword\">Password:</label><br>\n" +
                "            <input type=\"text\" id=\"fpassword\" required name=\"password\" value=\"\"><br>\n" +
                "\n" +
                "            <input id=\"endterbutton\" type=\"submit\" value=\"Enter\">\n" +
                "        </form>\n" +
                "\n" +
                "    </div>");
        printWriter.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println("got it: "+login+" "+password);
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String dburl = "jdbc:postgresql://localhost:5432/studentservlet";
            connection = DriverManager.getConnection(dburl, "postgres", "admin");

            String sql = "select * from register";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            int cookieCount=1;
            while (resultSet.next()){
                if (resultSet.getString("login").equals(login)&&resultSet.getString("password").equals(password)){
                    Cookie newCookie = new Cookie(login,password);
                    newCookie.setMaxAge(120);//set 120 second life for cookie
                    response.addCookie(newCookie);
                    cookieCount++;
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        doGet(request,response);
    }
    }
