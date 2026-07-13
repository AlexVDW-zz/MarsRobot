package za.co.redbadger.mars_robots;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class GroundControlTest {
    @Test
    void shouldReturnTrueIfSmellIsPresentAtPosition() {
        GroundControl groundControl = new GroundControl(5, 3);
        groundControl.signalLost(2, 3);
        assertTrue(groundControl.checkSmell(2, 3));
    }

    @Test
    void shouldReturnFalseIfSmellIsNotPresentAtPosition() {
        GroundControl groundControl = new GroundControl(5, 3);
        groundControl.signalLost(2, 3);
        assertFalse(groundControl.checkSmell(3, 2));
    }
}
