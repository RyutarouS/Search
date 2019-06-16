import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class FooTest {

    @Mock
    Hoge hoge;


    @InjectMocks
    Foo sample;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn("test2 ").when(hoge).getName(1);

    }

    @Test
    public void test() {
        System.out.println(sample.getHoge());
        assertEquals("test1", sample.getHoge());
    }
}
