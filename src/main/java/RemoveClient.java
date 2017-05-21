import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;


public class RemoveClient extends PostgresServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><Head><Title>Remove client</Title></Head>");
        out.println("<Body><H1>Remove client</H1>");

        try {
            // Declare our statement
            PreparedStatement statement;
            statement = DBConnection.prepareStatement("DELETE FROM user_info.user_info WHERE NIF = ?");
            statement.clearParameters();
            statement.setInt(1, Integer.parseInt(request.getParameter("NIF")));

            // Perform the query and render the results
            statement.executeUpdate();
            statement.close();

            out.println("<h3>Delete successful</h3>");

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
