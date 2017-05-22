import org.apache.commons.lang.StringEscapeUtils;
import org.glassfish.jersey.server.ParamException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;


public class AddClient extends PostgresServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><Head><Title>Add client</Title></Head>");
        out.println("<Body><H1>Add client</H1>");

        // Escape HTML to avoid XSS
        String name = StringEscapeUtils.escapeHtml(request.getParameter("name"));
        String address = StringEscapeUtils.escapeHtml(request.getParameter("address"));
        String phone_number = StringEscapeUtils.escapeHtml(request.getParameter("phone_number"));

        if (name == null || address == null || phone_number == null) {
            out.println("<HTML>" +
                    "<Head><Title>" +
                    "User info: Error" +
                    "</Title></Head>\n<Body>" +
                    "<P>Adding client failed: Empty parameter" +
                    "</P></Body></HTML>");
            return;
        }

        try {
            // Declare our statement
            PreparedStatement statement;
            statement = DBConnection.prepareStatement("INSERT INTO user_info.user_info VALUES (?, ?, ?, ?)");
            statement.clearParameters();

            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(request.getParameter("NIF")));
            statement.setString(3, address);
            statement.setString(4, phone_number);

            // Perform the query and render the results
            statement.executeUpdate();
            statement.close();

            out.println("<h3>Update successful</h3>");

        } catch (Exception ex) {
            out.println("<HTML>" +
                    "<Head><Title>" +
                    "User info: Error" +
                    "</Title></Head>\n<Body>" +
                    "<P>Adding client failed: " +
                    ex.getMessage() + "</P></Body></HTML>");
            return;
        }
        out.close();
    }
}
