package model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Tests for Event
public class EventTest {

    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Outfit saved."); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Outfit saved.", event.getDescription());
        assertEquals(date, event.getDate());
        assertFalse(event.equals(null));
        assertFalse(event.equals("Outfit saved"));
        Event e2 = new Event("Outfit not saved");
        assertNotEquals(e2.hashCode(), event.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Outfit saved.", event.toString());
    }
}
