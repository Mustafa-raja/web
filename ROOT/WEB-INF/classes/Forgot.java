import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Forgot extends HttpServlet {
    static final String DB_URL = "jdbc:mysql://localhost:3306/regandlogin?zeroDateTimeBehavior=CONVERT_TO_NULL";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String s1 = request.getParameter("Email");
        String rand = java.util.UUID.randomUUID().toString();
        String s3 = rand;
        s3 = encryptThisString(s3);
        String checkemail;
        Boolean n = true;
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
                checkemail = resultset.getString(2);
                if (s1.trim().equals(checkemail.trim())) {

                    statement.execute("UPDATE `userinfo` SET `Password`='" + s3 + "' WHERE Email = '" + s1 + "';");

                    Class.forName("javax.mail.Authenticator");
                    Mailer.send1(s1.trim(), "Password", "Your new password is " + rand);
                    out.println("Password updated and has been sent to you at your email.");
                    out.println("<script>");
                    out.println("location.replace(\"/relog.html\")");
                    out.println("</script>");
                    n = true;
                    break;
                } else {
                    n = false;
                }
//                out.println(resultset.getString(1));
//                out.println(resultset.getString(2));
//                out.println(resultset.getString(3));
            }
            if (n == false) {
                out.println("<script>");
                out.println("location.replace(\"/ForgotPass_WR.html\")");
                out.println("</script>");
                out.println("NO ACCOUNT WITH SUCH CREDENTIALS WAS FOUND :(");
            }
            resultset.close();
            statement.close();
            connection.close();
            System.out.println("Successfully done");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}