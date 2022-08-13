import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;
public class HelloWorldExample extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String x= request.getParameter("num");
        String y = request.getParameter("sp");
        String z = request.getParameter("ep");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        int a = Integer.parseInt(x);
        int b = Integer.parseInt(y);
        int c = Integer.parseInt(z);
        while (b<=c)
        {
            out.println("<p>"+ a+" *"+ b +" = "+ a*b +"</p>");
            b++;
        }
        //out.println("<h5>"+ x.toUpperCase() +"</h5>");
        //out.print("<h5>"+ y.toUpperCase() +"</h5>");
        out.println("</body>");
        out.println("</html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String x = request.getParameter("num");
        String y = request.getParameter("sp");
        String z = request.getParameter("ep");
        String [] arr = request.getParameterValues("lang");
        String w = request.getParameter("gender");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        int a = Integer.parseInt(x);
        int b = Integer.parseInt(y);
        int c = Integer.parseInt(z);
        int d = arr.length;
        out.print("<p>"+ w +"</p>");
        for(int i =0 ; i<d ; i++)
        {
            out.println("<p>"+ arr[i] +"</p>");
        }
        while (b<=c)
        {
            out.println("<p>"+ a+" *"+ b +" = "+ a*b +"</p>");
            b++;
        }
        //out.println("<h5>"+ x.toUpperCase() +"</h5>");
        //out.print("<h5>"+ y.toUpperCase() +"</h5>");
        out.println("</body>");
        out.println("</html>");
    }
}
