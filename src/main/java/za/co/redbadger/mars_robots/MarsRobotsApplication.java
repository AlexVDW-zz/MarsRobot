package za.co.redbadger.mars_robots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

//Main application class for the Mars Robots program. 
//This class is responsible for reading the input file, initializing the ground control system, and processing the robot commands.
//The application reads the input file specified in the application properties, which contains the grid size and the initial positions and commands for each robot. 
//It then creates a GroundControl instance to manage the grid and track lost robots, and processes each robot's commands, printing the final position of each robot to the console.
@SpringBootApplication
public class MarsRobotsApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(MarsRobotsApplication.class, args);
	}

	//Autowired ResourceLoader to load the input file from the classpath
	@Autowired
    private ResourceLoader resourceLoader;

	//filepath of the input file specified in the application properties
	@Value("${input.file.path}")
	private String filePath;

	@Override
	public void run(String... args) {
		processFile();
	}

	//Method to process the input file and execute the robot commands
	//This method reads the input file line by line,
	//initializes the GroundControl instance with the grid size,
	//and processes each robot's commands, printing the final position of each robot to the console
	public void processFile(){
		Resource resource = resourceLoader.getResource("classpath:" + filePath);
	
		//try-with-resources statement to automatically close the BufferedReader after use
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
			//First line of the input file contains the grid size, which is used to initialize the GroundControl instance
			String line = reader.readLine(); 
			
			GroundControl groundControl;
			if(line == null || line.isEmpty()){
				System.err.println("File empty");
				//Exit the method if the file is empty
				return;
			}
			
			String[] values = line.split(" ");
			//Initialize the GroundControl instance with the maximum x and y coordinates of the grid
			groundControl = new GroundControl(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			
			//Read the remaining lines of the input file, which contain the initial positions and commands for each robot
			line = reader.readLine(); 
			while (line != null) {
				if (line.isEmpty()) {
					//Skip empty lines in the input file
					line = reader.readLine();
					continue;
				}
				values = line.split(" ");
				//Create a new Robot instance with the initial position and direction specified in the input file,
				//and associate it with the GroundControl instance
				Robot robot = new Robot(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2].charAt(0), groundControl);
				//Read the next line of the input file, which contains the commands for the robot
				line = reader.readLine();
				if (line == null) break;//Exit the loop if the commands line is null

				//Process the robot's commands
				String output = robot.process(line);
				//Print the final position of the robot to the console
				System.out.println(output);
				//Read the next line of the input file ie process the next robot
				line = reader.readLine();
			}
		} catch (IOException e) {
			//Print an error message if there is an issue reading the input file
			System.err.println("Error reading the file: " + e.getMessage());
		}
	}
}
