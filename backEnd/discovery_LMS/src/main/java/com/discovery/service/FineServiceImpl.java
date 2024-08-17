package com.discovery.service;
import static com.discovery.constants.Constants.DAILY_FINE_RATE;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.custom_exceptions.ResourceNotFoundException;
import com.discovery.dao.BorrowDao;
import com.discovery.dao.FineDao;
import com.discovery.dto.FineDTO;
import com.discovery.entities.Borrow;
import com.discovery.entities.Fine;
import com.discovery.entities.User;


@Service
@Transactional
public class FineServiceImpl {

//    private static final double DAILY_FINE_RATE = 5.0; // Fixed daily fine rate

    @Autowired
    private FineDao fineDao;

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private ModelMapper mapper;

    public List<FineDTO> getAllFines() {
        return fineDao.findAll().stream()
            .map(fine -> mapper.map(fine, FineDTO.class))
            .collect(Collectors.toList());
    }

    public FineDTO calculateFine(Long borrowId) {
        Borrow borrow = borrowDao.findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid borrow id"));

        long daysOverdue = ChronoUnit.DAYS.between(borrow.getDueDate(), LocalDate.now());
        
        if (daysOverdue > 0) {
            double fineAmount = daysOverdue * DAILY_FINE_RATE;
            Fine fine = fineDao.findByBorrow(borrow);
            if (fine == null) {
               fine = new Fine(borrow, borrow.getUser(), fineAmount, LocalDate.now(), false);
            } else {
                fine.setFineAmount(fineAmount);
                fine.setFineDate(LocalDate.now());
            }
            Fine savedFine = fineDao.save(fine);
            
            FineDTO dto =  mapper.map(savedFine, FineDTO.class);
            dto.setBorrowId(borrowId);
            return dto;
        } else {
            throw new RuntimeException("Borrow is not overdue, no fine applicable.");
        }
    }

    
    public FineDTO markFineAsPaid(Long fineId) {
        Fine fine = fineDao.findById(fineId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid fine id"));
        fine.setPaid(true);
        Fine updatedFine = fineDao.save(fine);
        return mapper.map(updatedFine, FineDTO.class);
    }
    
 
}
