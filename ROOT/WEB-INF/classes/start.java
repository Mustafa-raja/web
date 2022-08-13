import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class start extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            out.println("<script>");
            out.println("location.replace(\"/finalPage.html\")");
            out.println("</script>");
        }
        else {
            out.println("<script>");
            out.println("location.replace(\"/login.html\")");
            out.println("</script>");
        }
    }
    }