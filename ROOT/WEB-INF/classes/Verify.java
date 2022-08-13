import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Verify extends HttpServlet {
    static final String DB_URL = "jdbc:mysql://localhost:3306/regandlogin?zeroDateTimeBehavior=CONVERT_TO_NULL";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String s = request.getParameter("username");
        String checkuser;
        String checkVerify;
        Boolean n = false;
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\"/>");
        out.println("</head>");
        out.println("<center>");
        out.println("<center>");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, "root", "");
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM userinfo");
            while (resultset.next()) {
                checkuser = resultset.getString(5);
                checkVerify = resultset.getString(4);
                if (checkuser.trim().equals(s) && checkVerify.trim().equals("0")) {

                    statement.execute("UPDATE `userinfo` SET `verify`='1' WHERE URL = '" + s + "';");
                    out.println("<script>");
                    out.println("location.replace(\"/login.html\")");
                    out.println("</script>");
                    n = true;
                    break;
                } else if (checkuser.trim().equals(s) && checkVerify.trim().equals("1")) {
                    out.println("Your account has already been Verified");
                    n = false;
                    break;
                }
//                out.println(resultset.getString(1));
//                out.println(resultset.getString(2));
//                out.println(resultset.getString(3));
            }
            if (n == false) {
                out.println("NO ACCOUNT WITH SUCH CREDENTIALS WAS FOUND :(");
            }
            n=true;
            resultset.close();
            statement.close();
            connection.close();
            System.out.println("Successfully done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
