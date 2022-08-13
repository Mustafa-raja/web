import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.NoSuchAlgorithmException;

public class register extends HttpServlet {

    static final String DB_URL = "jdbc:mysql://localhost:3306/regandlogin?zeroDateTimeBehavior=CONVERT_TO_NULL";
    boolean n = true;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String s1 = request.getParameter("Username");
        String s2 = request.getParameter("email");
        String s3 = request.getParameter("password");
        String s4 = "0";
        String s5 = encryptThisString(s1);
        String baseURL = "http://localhost:8080/Verify?username="+s5;
        s3 = encryptThisString(s3);
        String checkuser;
        String checkemail;
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
                if(s1.trim().equals(checkuser.trim()) )
                {
                    out.println("<script>");
                    out.println("location.replace(\"/reg_wrongcreds.html\")");
                    out.println("</script>");
                    //out.println("<p style = \"color:red;\">An Account with this Username already exists</p>");
                    n=false;
                    break;
                } else if (s2.trim().equals(checkemail.trim()) ) {
                    out.println("<script>");
                    out.println("location.replace(\"/reg_wrongcreds.html\")");
                    out.println("</script>");
                    //out.println("<p style = \"color:red;\">An Account with this Email already exists</p>");
                    n=false;
                    break;
                }
//                out.println(resultset.getString(1));
//                out.println(resultset.getString(2));
//                out.println(resultset.getString(3));
            }
            if(n==true) {
                System.out.println("above insert");
                String ai = "('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "')";
                statement.execute("INSERT INTO `userinfo`(`Username`, `Email`, `Password`, `verify`, `URL`) VALUES  " + ai);
                System.out.println("Above mail");
                Class.forName("javax.mail.Authenticator");
                Mailer.send1(s2.trim(),"Welcome","I very warmly welcome you to my first ever website. Click on the link below to GET STARTED \n"+ baseURL );
                out.println("<script>");
                out.println("location.replace(\"/verification.html\")");
                out.println("</script>");
                out.println("Mail command executed");
            }
            n=true;
            resultset.close();
            statement.close();
            connection.close();
            System.out.println("Successfully done");
        }catch(Exception e){e.printStackTrace();}
    }
    public static String encryptThisString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }


        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}


