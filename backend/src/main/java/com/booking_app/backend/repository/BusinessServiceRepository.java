package com.booking_app.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booking_app.backend.entity.BusinessService;

public interface BusinessServiceRepository extends JpaRepository<BusinessService, Long> {
    
    List<BusinessService> findByUser_Id(Long userId);
    Optional<BusinessService> findByIdAndUser_Id(Long id , Long userId);
}
