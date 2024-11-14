package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
