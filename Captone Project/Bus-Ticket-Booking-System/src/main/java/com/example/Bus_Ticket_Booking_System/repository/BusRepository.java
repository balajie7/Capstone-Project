package com.example.Bus_Ticket_Booking_System.repository;

import com.example.Bus_Ticket_Booking_System.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}