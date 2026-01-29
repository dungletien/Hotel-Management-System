package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    boolean existsByEmail(String email);

    Optional<Guest> findByEmailAndIsDeletedFalse(String email);

    Page<Guest> findAllByIsDeletedFalse(Pageable pageable);
    
    // Search by criteria
    @Query("SELECT g FROM Guest g WHERE g.isDeleted = false AND " +
           "(LOWER(g.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.phone) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Guest> searchGuests(@Param("keyword") String keyword, Pageable pageable);
    
    // Find by email
    Page<Guest> findByEmailContainingIgnoreCaseAndIsDeletedFalse(String email, Pageable pageable);
    
    // Find by phone
    Page<Guest> findByPhoneContainingAndIsDeletedFalse(String phone, Pageable pageable);
    
    // Find by loyalty points greater than or equal
    Page<Guest> findByLoyaltyPointsGreaterThanEqualAndIsDeletedFalse(Integer points, Pageable pageable);
}
