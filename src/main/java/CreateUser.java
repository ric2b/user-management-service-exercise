import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
//import org.postgresql.Driver;


public class CreateUser extends HttpServlet {

    private Connection DBConnection;

    // "init" sets up a database connection
    public void init(ServletConfig config) throws ServletException
    {
        String loginUser = USER;
        String loginPassword = PASSWORD;
        String loginUrl = "jdbc:postgresql://localhost:5432/postgres";
        //jdbc:postgresql://localhost:5432/postgres

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><Head><Title>User info</Title></Head>");
        out.println("<Body><H1>Create user</H1>");

        try {
            // Declare our statement
            Statement statement = DBConnection.createStatement();

            String query = "SELECT * from user_info.user_info";

            // Perform the query
            ResultSet rs = statement.executeQuery(query);

            out.println("<table border>");

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
