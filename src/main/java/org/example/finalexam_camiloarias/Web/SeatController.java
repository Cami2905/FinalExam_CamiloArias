package org.example.finalexam_camiloarias.Web;

import org.example.finalexam_camiloarias.Entities.Seat;
import org.example.finalexam_camiloarias.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//THIS IS THE GITHUB LINK TO MY PROJECT: https://github.com/Cami2905/FinalExam_CamiloArias.git

@Controller
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public String getSeats(Model model) {
        List<Seat> seats = seatService.getAllSeats();
        long remainingSeats = seats.stream().filter(seat -> !seat.isReserved()).count();
        model.addAttribute("seats", seats);
        model.addAttribute("remainingSeats", remainingSeats);
        return "seats";  // Thymeleaf template name
    }

    @PostMapping("/reserve/{seatId}")
    public String reserveSeat(@PathVariable String seatId, @RequestParam(required = false) String name, Model model) {
        seatService.reserveSeat(seatId, name);
        return "redirect:/seats";
    }


    @PostMapping("/cancel/{seatId}")
    public String cancelReservation(@PathVariable String seatId, Model model) {
        seatService.cancelReservation(seatId);
        return "redirect:/seats";
    }
}
