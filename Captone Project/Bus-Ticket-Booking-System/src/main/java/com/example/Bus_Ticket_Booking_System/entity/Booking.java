package com.example.Bus_Ticket_Booking_System.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Assuming a booking is associated with one bus
    @JoinColumn(name = "bus_id", nullable = false) // Foreign key
    private Bus bus;

    @ManyToOne // Assuming a booking is associated with one passenger
    @JoinColumn(name = "passenger_id", nullable = false) // Foreign key
    private Passenger passenger;

    private LocalDateTime bookingDate; // Changed to LocalDateTime for better date handling

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
