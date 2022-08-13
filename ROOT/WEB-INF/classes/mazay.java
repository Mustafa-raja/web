import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;

public class mazay extends  HttpServlet {
    static final String DB_URL = "jdbc:mysql://localhost:3306/department?zeroDateTimeBehavior=CONVERT_TO_NULL";
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("num");
        String lname = request.getParameter("sp");
        int dob = 10;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try{
//Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection (DB_URL,"root","");
            statement = connection.createStatement();

            String ai="('"+fname+"','"+lname+"','"+dob+"')";
            statement.execute("INSERT INTO `dept` (`FirstName`, `LastName`, `Rollno`) VALUES "+ai);

            resultset = statement.executeQuery("SELECT * FROM dept");
            while(resultset.next())
            {
                out.println(resultset.getString(1));
                out.println(resultset.getString(2));
                out.println(resultset.getString(3));
                out.println("yo");
            }

            resultset.close();
            statement.close();
            connection.close();
            System.out.println("Successfully done");
        }catch(Exception e){e.printStackTrace();}

    }
}
