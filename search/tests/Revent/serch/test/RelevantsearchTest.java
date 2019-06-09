package Revent.serch.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relevent.serch.Relevantsearch;

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
        String strB = "bccd";
        assertEquals(2, Relevantsearch.isRelevantScore(strA,strB));

        strA = "abcd";
        strB = "eccd";
        assertEquals(2, Relevantsearch.isRelevantScore(strA,strB));

        strA = "abcd";
        strB = "dd";
        assertEquals(3, Relevantsearch.isRelevantScore(strA,strB));

        strA = "abcd";
        strB = "cad";
        assertEquals(3, Relevantsearch.isRelevantScore(strA,strB));

        strA = "abcd";
        strB = "eccdea";
        assertEquals(4, Relevantsearch.isRelevantScore(strA,strB));

        strA = "abcdef";
        strB = "acdef";
        assertEquals(1, Relevantsearch.isRelevantScore(strA,strB));
    }
}
