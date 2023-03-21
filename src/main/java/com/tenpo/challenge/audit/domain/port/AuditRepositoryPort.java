package com.tenpo.challenge.audit.domain.port;

import com.tenpo.challenge.audit.domain.model.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuditRepositoryPort {

  Audit save(Audit audit);

  Page<Audit> findAll(Pageable pageable);

}
