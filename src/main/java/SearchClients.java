import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SearchClients extends PostgresServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><Head><Title>Client info</Title></Head>");
        out.println("<Body><H1>List clients</H1>");

        try {
            // Declare our statement
            PreparedStatement statement;
            statement = DBConnection.prepareStatement("SELECT * from user_info.user_info where LOWER(name) like LOWER(?)");
            statement.clearParameters();
            statement.setString(1, "%" + request.getParameter("name") + "%");

            // Perform the query
            ResultSet rs = statement.executeQuery();

            out.println("<table border>");
            out.println("<tr>" +
                    "<td>" + "name" + "</td>" +
                    "<td>" + "NIF" + "</td>" +
                    "<td>" + "address" + "</td>" +
                    "<td>" + "phone number" + "</td>" +
                    "</tr>");

            // Iterate through each row of rs
            while (rs.next()) {
                String m_name = rs.getString("name");
                String m_NIF = rs.getString("NIF");
                String m_address = rs.getString("address");
                String m_phone_number = rs.getString("phone_number");
                out.println("<tr>" +
                        "<td>" + m_name + "</td>" +
                        "<td>" + m_NIF + "</td>" +
                        "<td>" + m_address + "</td>" +
                        "<td>" + m_phone_number + "</td>" +
                        "</tr>");
            }

            out.println("</table></body></html>");
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
