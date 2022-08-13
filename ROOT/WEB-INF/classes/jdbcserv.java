
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class jdbcserv extends HttpServlet {
        static final String db_url="jdbc:mysql://localhost:3306/department";
        static final String db_drv="com.mysql.jdbc.driver";
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String fname= request.getParameter("FirstName");
            String lname= request.getParameter("SecondName");
            String dob = request.getParameter("Dob");
            String gender=request.getParameter("gender");
            String email=request.getParameter("email");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome</title>");
            out.println("</head>");
            out.println("<body>");
            Connection connection=null;
            Statement statement=null;
            ResultSet resultset=null;
            try{
                Class.forName(db_drv);
                connection=DriverManager.getConnection(db_url,"root","");
                statement=connection.createStatement();
                resultset = statement.
                String ai="('"+fname+"','"+lname+"','"+dob+"','"+gender+"','"+email+"')";
                //statement.execute("INSERT INTO `regist` (`FirstName`, `SecondName`, `DOB`, `Gender`, `Email`) VALUES "+ai);

                statement.close();

            }catch(Exception e)  {e.printStackTrace();}
            out.println("</body>");
            out.println("</html>");
        }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String fname= request.getParameter("FirstName");
            String lname= request.getParameter("SecondName");
            String dob = request.getParameter("Dob");
            String gender=request.getParameter("gender");
            String email=request.getParameter("email");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome</title>");
            out.println("</head>");
            out.println("<body>");

            Connection connection=null;
            Statement statement=null;
            ResultSet resultset=null;
            try{
                connection=DriverManager.getConnection(db_url,"root","");
                statement=connection.createStatement();
                String ai="('"+fname+"','"+lname+"','"+dob+"','"+gender+"','"+email+"')";
                statement.execute("INSERT INTO `regist` (`FirstName`, `SecondName`, `DOB`, `Gender`, `Email`) VALUES "+ai);
                statement.close();

            }catch(Exception e)  {e.printStackTrace();}
            out.println("</body>");
            out.println("</html>");
        }
    }
