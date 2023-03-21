package com.tenpo.challenge.audit.adapters.inbound.controller;

import com.tenpo.challenge.audit.domain.action.find.FindAllAuditUseCase;
import com.tenpo.challenge.audit.domain.model.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/audits")
public class AuditController {

  private final FindAllAuditUseCase findAllAuditUseCase;

  @GetMapping
  public ResponseEntity<Page<Audit>> getAudits(Pageable pageable) {
    return ResponseEntity.ok(this.findAllAuditUseCase.findAll(pageable));
  }

}
