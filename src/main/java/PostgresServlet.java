import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PostgresServlet extends HttpServlet {

    Connection DBConnection;

    // "init" sets up a database connection
    public void init(ServletConfig config) throws ServletException {
        String loginUser = System.getenv("db_user");
        String loginPassword = System.getenv("db_password");
        String loginUrl = "jdbc:postgresql://localhost:5432/postgres";

        // Load the PostgreSQL driver
        try
        {
            Class.forName("org.postgresql.Driver");
            DBConnection = DriverManager.getConnection(loginUrl, loginUser, loginPassword);

        } catch (ClassNotFoundException e) {
            System.err.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    void renderTable(PrintWriter out, ResultSet rs) {
        // Output stream to STDOUT
        out.println("<table border>");
        out.println("<tr>" +
                "<td>" + "name" + "</td>" +
                "<td>" + "NIF" + "</td>" +
                "<td>" + "address" + "</td>" +
                "<td>" + "phone number" + "</td>" +
                "</tr>");

        // Iterate through each row of rs
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</table></body></html>");
        out.close();
    }
}
