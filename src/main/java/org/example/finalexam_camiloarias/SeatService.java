package org.example.finalexam_camiloarias;

import org.example.finalexam_camiloarias.Entities.Seat;
import org.example.finalexam_camiloarias.Repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat reserveSeat(String seatId, String reservedBy) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        if (!seat.isReserved()) {
            seat.setReserved(true);
            seat.setReservedBy(reservedBy != null ? reservedBy : "Reserved"); // Set name if provided, otherwise leave blank
            return seatRepository.save(seat);
        } else {
            throw new IllegalStateException("Seat is already reserved");
        }
    }

    public Seat cancelReservation(String seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        if (seat.isReserved()) {
            seat.setReserved(false);
            seat.setReservedBy(null); // Clear the reservedBy field
            return seatRepository.save(seat);
        } else {
            throw new IllegalStateException("Seat is not reserved");
        }
    }
}
