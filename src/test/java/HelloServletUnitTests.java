import com.epam.inodin.jenkinstestwebapp.HelloServlet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HelloServletUnitTests {

    HelloServlet helloServlet = new HelloServlet();

    //Test will pass every time
    @Test
    void getInfoFromServlet_returnsString() {
        //given
        var expectedString = "Hello from servlet!";
        //when
        var resultString = helloServlet.getInfoFromServlet();
        //then
        assertEquals(expectedString, resultString);
    }
}
