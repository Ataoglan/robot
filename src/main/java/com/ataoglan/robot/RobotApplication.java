package com.ataoglan.robot;

import com.ataoglan.robot.core.Space;
import com.ataoglan.robot.input.Inputs;
import com.ataoglan.robot.input.RobotInput;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class RobotApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Inputs inputs = new Inputs();

		System.out.println("Create space with x y");
		String gridSpace = scanner.nextLine();

		inputs.setGridX(Integer.parseInt(gridSpace.split(" ")[0]));
		inputs.setGridY(Integer.parseInt(gridSpace.split(" ")[1]));

		System.out.println("How many robots in space?");
		int robotCount = scanner.nextInt();

		List<RobotInput> robotInputs = IntStream
				.range(0, robotCount)
				.boxed()
				.map(i -> {
					Scanner scannerCopy = new Scanner(System.in);
					System.out.println("Robot coordinates and direction? (ex: 1 2 E/N/W/S)");
					String robotCoordinates = scannerCopy.nextLine();

					System.out.println("Commands ?");
					String commands = scannerCopy.nextLine();

					RobotInput robotInput = new RobotInput();
					robotInput.setRobotId(i);
					robotInput.setX(Integer.parseInt(robotCoordinates.split(" ")[0]));
					robotInput.setY(Integer.parseInt(robotCoordinates.split(" ")[1]));
					robotInput.setDirection(robotCoordinates.split(" ")[2]);
					robotInput.setCommands(commands);
					return robotInput;
				}).collect(Collectors.toList());

		inputs.setRobotInputs(robotInputs);

		Space space = new Space();
		space.createSpace(inputs.getGridX(), inputs.getGridY());
		space.createRobots(inputs.getRobotInputs());
		space.runCommands();
	}

}
