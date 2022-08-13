import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Iterator;
import java.util.Properties;

public class servlet2 extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession s = request.getSession() ;
        ArrayList nigs;
        nigs=(ArrayList) s.getAttribute("name");
        Iterator i = nigs.iterator();
        PrintWriter out = response.getWriter();
        Properties p = System.getProperties();

        out.println("<html>");

        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\"/>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border=\"2px\">");
        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Properties</th>");
        out.println("</tr>");


        out.println("<h6>");

        Set <String> niga =  p.stringPropertyNames();
        Iterator k = niga.iterator();
        String l;
        String j;
        while(k.hasNext())
        {
            out.println("<tr>");
            out.println("<h6>");
            l= k.next().toString();
            j=l;
            out.println("<td>"+j+"</td>");
            j=p.getProperty( l);
            out.println("<td>"+j+"</td>");
            out.println("</h6>");
            out.println("</tr>");
        }
        out.println("</table>");
//        out.println("");
//        while (i.hasNext()) {
//            out.println(i.next());
//        }

        out.println("</body>");
        out.println("</html>");
    }
}