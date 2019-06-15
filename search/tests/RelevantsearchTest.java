import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelevantsearchTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isRelaventScore() {
        String strA = "abcd";
        String[] strBs = {"abcd", "abdd", "dd", "cad", "eccdea", "abcd", "a", "eccdeag", "absd", "absd"};
        int[] anses = {0, 1, 3, 3, 4, 0,3,5,1,1};
        for(int i = 0; i < strBs.length; i++){
            double numToOptimize = Math.max(strA.length(), strBs[i].length());
            System.out.println(strA + " vs. " + strBs[i]);
            System.out.println(Relevantsearch.isRelevantScore(strA,strBs[i]));
            assertEquals(anses[i]/numToOptimize, Relevantsearch.isRelevantScore(strA,strBs[i]));
        }
    }
}
