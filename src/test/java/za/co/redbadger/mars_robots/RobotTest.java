package za.co.redbadger.mars_robots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RobotTest {

    @Test
    void secondRobotOnSameScentedTileIsNotLost() {
        GroundControl groundControl = new GroundControl(5, 3);

        Robot first = new Robot(2, 3, 'N', groundControl);
        first.process("F");
        assertEquals("2 3 N LOST", first.toString());

        Robot second = new Robot(2, 3, 'N', groundControl);
        second.process("F");
        assertEquals("2 3 N", second.toString());
    }

    @Test
    void robotAtMinXMinYMovingWestIsLost() {
        Robot robot = new Robot(0, 0, 'W', new GroundControl(5, 3));
        robot.process("F");
        assertEquals("0 0 W LOST", robot.toString());
    }

    @Test
    void robotAtMinXMinYMovingSouthIsLost() {
        Robot robot = new Robot(0, 0, 'S', new GroundControl(5, 3));
        robot.process("F");
        assertEquals("0 0 S LOST", robot.toString());
    }

    @Test
    void robotAtMaxXMaxYMovingNorthIsLost() {
        Robot robot = new Robot(5, 3, 'N', new GroundControl(5, 3));
        robot.process("F");
        assertEquals("5 3 N LOST", robot.toString());
    }

    @Test
    void robotAtMaxXMaxYMovingEastIsLost() {
        Robot robot = new Robot(5, 3, 'E', new GroundControl(5, 3));
        robot.process("F");
        assertEquals("5 3 E LOST", robot.toString());
    }

    @Test
    void turnLeftWhileFacingEastResultsInNorth() {
        Robot robot = new Robot(2, 2, 'E', new GroundControl(5, 3));
        robot.process("L");
        assertEquals("2 2 N", robot.toString());
    }

    @Test
    void turnLeftWhileFacingWestResultsInSouth() {
        Robot robot = new Robot(2, 2, 'W', new GroundControl(5, 3));
        robot.process("L");
        assertEquals("2 2 S", robot.toString());
    }

    @Test
    void turnLeftWhileFacingNorthResultsInWest() {
        Robot robot = new Robot(2, 2, 'N', new GroundControl(5, 3));
        robot.process("L");
        assertEquals("2 2 W", robot.toString());
    }

    @Test
    void turnLeftWhileFacingSouthResultsInEast() {
        Robot robot = new Robot(2, 2, 'S', new GroundControl(5, 3));
        robot.process("L");
        assertEquals("2 2 E", robot.toString());
    }

    @Test
    void turnRightWhileFacingEastResultsInSouth() {
        Robot robot = new Robot(2, 2, 'E', new GroundControl(5, 3));
        robot.process("R");
        assertEquals("2 2 S", robot.toString());
    }

    @Test
    void turnRightWhileFacingWestResultsInNorth() {
        Robot robot = new Robot(2, 2, 'W', new GroundControl(5, 3));
        robot.process("R");
        assertEquals("2 2 N", robot.toString());
    }

    @Test
    void turnRightWhileFacingNorthResultsInEast() {
        Robot robot = new Robot(2, 2, 'N', new GroundControl(5, 3));
        robot.process("R");
        assertEquals("2 2 E", robot.toString());
    }

    @Test
    void turnRightWhileFacingSouthResultsInWest() {
        Robot robot = new Robot(2, 2, 'S', new GroundControl(5, 3));
        robot.process("R");
        assertEquals("2 2 W", robot.toString());
    }
}
