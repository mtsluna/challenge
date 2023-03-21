package com.tenpo.challenge.audit.adapters.inbound.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.tenpo.challenge.audit.domain.action.find.FindAllAuditUseCase;
import com.tenpo.challenge.audit.domain.model.Audit;
import java.util.List;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuditControllerTest {

  @InjectMocks
  private AuditController auditController;

  @Mock
  private FindAllAuditUseCase findAllAuditUseCase;

  @Test
  void getAudits() {

    var pageable = PageRequest.of(0, 2);

    Page<Audit> audits = new PageImpl<>(List.of(
        Audit
            .builder()
            .id(1L)
            .path("/sarasa")
            .method("GET")
            .response("body")
            .build(),
        Audit
            .builder()
            .id(2L)
            .path("/sarasa")
            .method("GET")
            .response("body")
            .build()
    ));

    Mockito.when(this.findAllAuditUseCase.findAll(pageable))
        .thenReturn(audits);

    ResponseEntity<Page<Audit>> responseEntityAudit = this.auditController.getAudits(pageable);
    Assertions.assertEquals(HttpStatus.OK, responseEntityAudit.getStatusCode());
    Assertions.assertEquals(audits, responseEntityAudit.getBody());

  }
}