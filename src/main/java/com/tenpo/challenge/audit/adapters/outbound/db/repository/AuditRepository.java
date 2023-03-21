package com.tenpo.challenge.audit.adapters.outbound.db.repository;

import com.tenpo.challenge.audit.adapters.outbound.db.entity.AuditEntity;
import com.tenpo.challenge.audit.domain.model.Audit;
import com.tenpo.challenge.audit.domain.port.AuditRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuditRepository implements AuditRepositoryPort {

  private final AuditJpaRepository auditJpaRepository;

  @Override
  public Audit save(Audit audit) throws IllegalArgumentException {
    AuditEntity auditEntity = this.auditJpaRepository.save(AuditEntity
        .builder()
        .method(audit.getMethod())
        .path(audit.getPath())
        .response(audit.getResponse())
        .build()
    );

    return Audit
        .builder()
        .id(auditEntity.getId())
        .method(auditEntity.getMethod())
        .path(auditEntity.getPath())
        .response(auditEntity.getResponse())
        .build();
  }

  @Override
  public Page<Audit> findAll(Pageable pageable) {
    return this.auditJpaRepository.findAll(pageable).map((value) -> Audit
        .builder()
        .id(value.getId())
        .response(value.getResponse())
        .path(value.getPath())
        .method(value.getMethod())
        .build());
  }
}
