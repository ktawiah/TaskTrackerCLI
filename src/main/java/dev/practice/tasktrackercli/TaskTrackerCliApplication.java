package dev.practice.tasktrackercli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class TaskTrackerCliApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerCliApplication.class, args);
	}

}
