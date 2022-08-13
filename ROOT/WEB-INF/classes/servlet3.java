import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;
import java.util.ArrayList;

public class servlet3 extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ArrayList <String> nigs=new ArrayList<String>();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        String x = request.getParameter("num");
        String y = request.getParameter("sp");
        nigs.add(x);
        nigs.add(y);
        HttpSession session = request.getSession();
        session.setAttribute("name",nigs);
        System.out.println(x);
        out.println("<a href= \"/doublemazay\">Home</a>");
        out.println("</body>");
        out.println("</html>");
    }
}