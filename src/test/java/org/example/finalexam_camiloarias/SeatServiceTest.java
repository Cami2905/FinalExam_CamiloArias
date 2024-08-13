package org.example.finalexam_camiloarias;

import com.yourcompany.seatreservationsystem.model.Seat;
import org.example.finalexam_camiloarias.Repositories.SeatRepository;
import org.example.finalexam_camiloarias.Entities.Seat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SeatServiceTest {

    @Test
    void reserveSeat_success() {

        String seatId = "1A";
        String reservedBy = "John Doe";
        Seat seat = new Seat(seatId, false, null);

        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        Seat reservedSeat = seatService.reserveSeat(seatId, reservedBy);

        assertTrue(reservedSeat.isReserved());
        assertEquals(reservedBy, reservedSeat.getReservedBy());
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, times(1)).save(seat);
    }

    @Test
    void reserveSeat_alreadyReserved() {

        String seatId = "1A";
        Seat seat = new Seat(seatId, true, "Jane Doe");

        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            seatService.reserveSeat(seatId, "John Doe");
        });

        assertEquals("Seat is already reserved", exception.getMessage());
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, times(0)).save(any(Seat.class));
    }

    @Test
    void cancelReservation_success() {
        // Arrange
        String seatId = "1A";
        Seat seat = new Seat(seatId, true, "John Doe");

        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        // Act
        Seat canceledSeat = seatService.cancelReservation(seatId);

        // Assert
        assertFalse(canceledSeat.isReserved());
        assertNull(canceledSeat.getReservedBy());
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, times(1)).save(seat);
    }
}