package org.example.finalexam_camiloarias.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Seat {

    @Id
    private String seatId;  // e.g., 1A, 2B, etc.

    private boolean reserved;

    private String reservedBy;  // Name of the person who reserved the seat

    // Constructors
    public Seat() {
    }

    public Seat(String seatId, boolean reserved, String reservedBy) {
        this.seatId = seatId;
        this.reserved = reserved;
        this.reservedBy = reservedBy;
    }

    // Getters and Setters
    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }
}
