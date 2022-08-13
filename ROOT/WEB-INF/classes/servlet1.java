import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;


public class servlet1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String x= request.getParameter("num");
        String y = request.getParameter("sp");

        out.println("<html>");
        out.println(x);
        out.println(y);
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("agdfka");
        Cookie c1 = new Cookie("num",x) ;
        Cookie c2 = new Cookie("sp",y) ;
        c1.setMaxAge(600);
        c2.setMaxAge(600);
        response.addCookie(c1);
        response.addCookie(c2);

        System.out.println("hasgdkal");
        System.out.println("ki haal chaal ae");
        out.println("<a href= \"/doublemazay\">Home</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}