//import static junit.framework.Assert.fail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddClientTest {
    private AddClient classToTest;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private StringWriter response_writer;

    @Mock
    private Connection databaseMock;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        classToTest = new AddClient();
        classToTest.DBConnection = databaseMock;

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        response_writer = new StringWriter();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void correctParameters() throws Exception {
        when(databaseMock.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        given(req.getParameter("name")).willReturn("Roberto");
        given(req.getParameter("NIF")).willReturn("56789");
        given(req.getParameter("address")).willReturn("Over there");
        given(req.getParameter("phone_number")).willReturn("2468");

        when(resp.getWriter()).thenReturn(new PrintWriter(response_writer));
        try {
            classToTest.doPost(req, resp);
        }
        catch (ServletException ex) {
            Assert.fail("Exception thrown");
        }

        Assert.assertEquals("<HTML><Head><Title>Add client</Title></Head>\n" +
                "<Body><H1>Add client</H1>\n" +
                "<h3>Update successful</h3>\n", response_writer.toString());
    }

    @Test
    public void noNameGiven() throws Exception {
        when(databaseMock.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        given(req.getParameter("NIF")).willReturn("56789");
        given(req.getParameter("address")).willReturn("Over there");
        given(req.getParameter("phone_number")).willReturn("2468");

        when(resp.getWriter()).thenReturn(new PrintWriter(response_writer));
        try {
            classToTest.doPost(req, resp);
        }
        catch (ServletException ex) {
            Assert.fail("Exception thrown");
        }

        Assert.assertEquals("<HTML><Head><Title>Add client</Title></Head>\n" +
                "<Body><H1>Add client</H1>\n" +
                "<HTML><Head><Title>User info: Error</Title></Head>\n" +
                "<Body><P>Adding client failed: Empty parameter</P></Body></HTML>\n", response_writer.toString());
    }
}
