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

@SpringBootApplication
public class MarsRobotsApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(MarsRobotsApplication.class, args);
	}

	@Autowired
    private ResourceLoader resourceLoader;

	@Value("${input.file.path}")
	private String filePath;

	@Override
	public void run(String... args) {
		processFile();
	}

	public void processFile(){
		Resource resource = resourceLoader.getResource("classpath:" + filePath);
	
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
			String line = reader.readLine(); 
			
			GroundControl groundControl;
			if(line == null || line.isEmpty()){
				System.err.println("File empty");
				return;
			}
			
			String[] values = line.split(" ");
			groundControl = new GroundControl(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			
			line = reader.readLine(); 
			while (line != null) {
				if (line.isEmpty()) {
					line = reader.readLine();
					continue;
				}
				values = line.split(" ");
				Robot robot = new Robot(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2].charAt(0), groundControl);
				line = reader.readLine();
				if (line == null) break;
				String output = robot.process(line);
				System.out.println(output);
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		}
	}
}
