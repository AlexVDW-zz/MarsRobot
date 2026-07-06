package za.co.redbadger.mars_robots;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class RobotsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testSingleRobot(){
		Robot robot = new Robot(1, 1, 'E', new GroundControl(5, 3));
		robot.process("RFRFRFRF");
		Assert.isTrue("1 1 E".equals(robot.toString()), "Robot final position is incorrect");
	}

	@Test
	void testMultipleRobots(){

		GroundControl groundControl = new GroundControl(5, 3);

		Robot robot1 = new Robot(1, 1, 'E', groundControl);
		robot1.process("RFRFRFRF");
		Assert.isTrue("1 1 E".equals(robot1.toString()), "Robot final position is incorrect");

		Robot robot2 = new Robot(3, 2, 'N', groundControl);
		robot2.process("FRRFLLFFRRFLL");
		Assert.isTrue("3 3 N LOST".equals(robot2.toString()), "Robot final position is incorrect");

		Robot robot3 = new Robot(0, 3, 'W', groundControl);
		robot3.process("LLFFFLFLFL");
		Assert.isTrue("2 3 S".equals(robot3.toString()), "Robot final position is incorrect");
	}
}
