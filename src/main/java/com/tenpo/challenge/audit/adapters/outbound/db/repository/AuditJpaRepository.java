package com.tenpo.challenge.audit.adapters.outbound.db.repository;

import com.tenpo.challenge.audit.adapters.outbound.db.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditJpaRepository extends JpaRepository<AuditEntity, Long> { }
