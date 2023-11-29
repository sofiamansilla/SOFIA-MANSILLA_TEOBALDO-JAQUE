package com.dentalClinic.dental.repository;

import com.dentalClinic.dental.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <Address, Long> {
}
