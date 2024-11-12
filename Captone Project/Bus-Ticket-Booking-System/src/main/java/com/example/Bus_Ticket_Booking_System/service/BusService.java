package com.example.Bus_Ticket_Booking_System.service;

import com.example.Bus_Ticket_Booking_System.entity.Bus;
import com.example.Bus_Ticket_Booking_System.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = getBusById(id);
        if (busDetails.getBusNumber() != null) {
            bus.setBusNumber(busDetails.getBusNumber());
        }
        if (busDetails.getDriverName() != null) {
            bus.setDriverName(busDetails.getDriverName());
        }
        if (busDetails.getRoute() != null) {
            bus.setRoute(busDetails.getRoute());
        }
        if (busDetails.getSeatCapacity() > 0) {
            bus.setSeatCapacity(busDetails.getSeatCapacity());
        }
        return busRepository.save(bus);
    }


    public void deleteBus(Long id) {
        Bus bus = getBusById(id);
        busRepository.delete(bus);
    }
}
