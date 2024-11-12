package com.example.Bus_Ticket_Booking_System.repository;

import com.example.Bus_Ticket_Booking_System.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}