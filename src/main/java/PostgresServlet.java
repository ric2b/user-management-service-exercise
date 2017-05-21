import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class PostgresServlet extends HttpServlet {

    Connection DBConnection;

    // "init" sets up a database connection
    public void init(ServletConfig config) throws ServletException
    {
        String loginUser = USER;
        String loginPassword = PASSWORD;
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
}
