package com.example.Bus_Ticket_Booking_System.service;


import com.example.Bus_Ticket_Booking_System.entity.Booking;
import com.example.Bus_Ticket_Booking_System.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = getBookingById(id);

        if (bookingDetails.getBus() != null) {
            booking.setBus(bookingDetails.getBus());
        }
        if (bookingDetails.getPassenger() != null) {
            booking.setPassenger(bookingDetails.getPassenger());
        }
        if (bookingDetails.getBookingDate() != null) {
            booking.setBookingDate(bookingDetails.getBookingDate());
        }
        return bookingRepository.save(booking);
    }


    public void deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }
}


