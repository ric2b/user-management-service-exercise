import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class GetClient extends PostgresServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><Head><Title>Get client</Title></Head>");
        out.println("<Body><H1>Get client</H1>");

        try {
            // Declare our statement
            PreparedStatement statement;
            statement = DBConnection.prepareStatement("SELECT * from user_info.user_info where NIF = ?");
            statement.clearParameters();
            statement.setInt(1, Integer.parseInt(request.getParameter("NIF")));

            // Perform the query and render the results
            renderTable(response.getWriter(), statement.executeQuery());
            statement.close();

        } catch (Exception ex) {
            out.println("<HTML>" +
                    "<Head><Title>" +
                    "User info: Error" +
                    "</Title></Head>\n<Body>" +
                    "<P>SQL error in doGet: " +
                    ex.getMessage() + "</P></Body></HTML>");
            return;
        }
        out.close();
    }
}
