package org.example.finalexam_camiloarias;

import org.example.finalexam_camiloarias.Entities.Seat;
import org.example.finalexam_camiloarias.Repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FinalExamCamiloAriasApplication implements CommandLineRunner {

	@Autowired
	private SeatRepository seatRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinalExamCamiloAriasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> seatIds = Arrays.asList(
				"1A", "1B", "1C", "1D", "1E",
				"2A", "2B", "2C", "2D", "2E",
				"3A", "3B", "3C", "3D", "3E",
				"4A", "4B", "4C", "4D", "4E"
		);

		for (String seatId : seatIds) {
			if (!seatRepository.existsById(seatId)) {
				seatRepository.save(new Seat(seatId, false, null));
			}
		}
	}
}
