package tracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    void test() {
        assertEquals(5, 2 + 3);
    }
    @Test
    void testRepeatEmail() {

        assertEquals("correct",Main.validateInput("James Jones james@james.com"));
    }
}
