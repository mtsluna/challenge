package com.tenpo.challenge.audit.domain.action.find;

import com.tenpo.challenge.audit.domain.model.Audit;
import com.tenpo.challenge.audit.domain.port.AuditRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllAuditService implements FindAllAuditUseCase {

  private final AuditRepositoryPort auditRepositoryPort;

  @Override
  public Page<Audit> findAll(Pageable pageable) {
    return this.auditRepositoryPort.findAll(pageable);
  }
}
