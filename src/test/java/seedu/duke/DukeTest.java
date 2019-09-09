package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void getUITest() {
        Duke.getUI();
        System.out.println("JUnit testing is running");
        int x = 2;
        assertEquals(1, x);
    }
}
