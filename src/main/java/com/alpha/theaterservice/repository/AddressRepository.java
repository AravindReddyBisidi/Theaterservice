package com.alpha.theaterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alpha.theaterservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}