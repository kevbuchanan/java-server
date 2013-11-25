import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import kevin.server.*;
import kevin.directory_app.*;

@RunWith(JUnit4.class)
public class DirectoryHandlerTest {

    public Request request = mock(Request.class);
    public Callable app = mock(Callable.class);
    public DirectoryHandler handler = new DirectoryHandler(app);

    @Before
    public void setup() {
        request.method = "GET";
        request.route = "/test";
        request.baseDirectory = "resources";
    }

    @Test
    public void returnsSucces() {
        assertEquals(200, handler.call(request).status);
    }

    @Test
    public void returnsContent() {
        handler.call(request);
        assertThat(handler.buildDirectoryContents(), containsString(">test.txt</a></li>"));
    }

    @Test
    public void returnsNotAllowed() {
        request.method = "POST";
        assertEquals(405, handler.call(request).status);
    }

    @Test
    public void callsNextApp() {
        when(app.call(request)).thenReturn(new Response(200));
        request.route = "/parameters";
        handler.call(request);
        verify(app).call(request);
    }
}
