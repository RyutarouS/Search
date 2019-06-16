import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelevantControllerTest {

    @Mock
    DataContainer container = new DataContainer();

    @InjectMocks
    RelevantController controller = new RelevantController();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void giveToSearch() {
        String strA = "abcd";
        String strB = "abc";
        when(container.getStrA()).thenReturn(strA);
        when(container.getStrB()).thenReturn(strB);

        controller.giveToSearch();
        Double actual = controller.getScore();

        Double expected = 1D;
        assertEquals(expected, actual);
    }
}
