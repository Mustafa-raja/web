import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
public class Login extends HttpServlet {
    static final String DB_URL = "jdbc:mysql://localhost:3306/regandlogin?zeroDateTimeBehavior=CONVERT_TO_NULL";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session1 = request.getSession(false);
        if(session1 != null)
        {
            out.println("<script>");
            out.println("location.replace(\"/finalPage.html\")");
            out.println("</script>");
        }
        String s1 = request.getParameter("username");
        String s3 = request.getParameter("password");
        s3 = encryptThisString(s3);
        System.out.println(s3);
        String checkuser;
        String checkemail;
        String checkpass;
        Boolean n = true;
        String vrify ;
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\"/>");
        out.println("</head>");
        out.println("<center>");
        out.println("<h2>Ki haal chaal ae ?</h2>");
        out.println("Username : " + s1 + "<br>");
        out.println("Password : " + s3 + "<br>");
        out.println("<center>");
        Connection connection=null;
        Statement statement=null;
        ResultSet resultset=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection (DB_URL,"root","");
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM userinfo");
            while(resultset.next())
            {
                checkuser = resultset.getString(1);
                checkemail = resultset.getString(2);
                checkpass = resultset.getString(3);
                vrify = resultset.getString(4);
                if(( s1.trim().equals(checkuser.trim()) || s1.trim().equals(checkemail.trim()) ) && ( s3.trim().equals(checkpass.trim()) ) && (vrify.trim().equals("1")))
                {
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(600);
                    out.println("<script>");
                    out.println("location.replace(\"/finalPage.html\")");
                    out.println("</script>");
                    n = true;
                    break;
                }
                else if(( s1.trim().equals(checkuser.trim()) || s1.trim().equals(checkemail.trim()) ) && ( s3.trim().equals(checkpass.trim()) ) && (vrify.trim().equals("0")))
                {
                    out.println("<script>");
                    out.println("location.replace(\"/verification.html\")");
                    out.println("</script>");
                    n = true;
                    break;
                }
                else{
                    n = false;
                }
//                out.println(resultset.getString(1));
//                out.println(resultset.getString(2));
//                out.println(resultset.getString(3));
            }
            if(n == false)
            {
                out.println("<script>");
                out.println("location.replace(\"/login_wrongcreds.html\")");
                out.println("</script>");
                out.println("NO ACCOUNT WITH SUCH CREDENTIALS WAS FOUND :(");
            }
            resultset.close();
            statement.close();
            connection.close();
            System.out.println("Successfully done");
        }catch(Exception e){e.printStackTrace();}
    }
    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}