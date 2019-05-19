import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelaventsearchTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isRelaventScore() {
        String strA = "abcd";
        String strB = "bccd";
        //assertEquals(2, Relaventsearch.isRelaventScore(strA,strB));

        strA = "abcd";
        strB = "eccd";
        assertEquals(2, Relaventsearch.isRelaventScore(strA,strB));

        strA = "abcd";
        strB = "dd";
        assertEquals(3, Relaventsearch.isRelaventScore(strA,strB));

        strA = "abcd";
        strB = "cad";
        assertEquals(3, Relaventsearch.isRelaventScore(strA,strB));

        strA = "abcd";
        strB = "eccdea";
        assertEquals(4, Relaventsearch.isRelaventScore(strA,strB));
    }
}
