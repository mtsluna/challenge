package com.tenpo.challenge.audit.domain.action.find;

import com.tenpo.challenge.audit.domain.model.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllAuditUseCase {

  Page<Audit> findAll(Pageable pageable);

}
