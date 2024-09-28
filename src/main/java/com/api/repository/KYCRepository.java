package com.api.repository;

import com.api.entity.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KYC, Long> {
}