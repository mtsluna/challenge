package com.tenpo.challenge.audit.domain.action.find;

import static org.junit.jupiter.api.Assertions.*;

import com.tenpo.challenge.audit.domain.model.Audit;
import com.tenpo.challenge.audit.domain.port.AuditRepositoryPort;
import java.util.List;
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

@ExtendWith(MockitoExtension.class)
class FindAllAuditServiceTest {

  @InjectMocks
  private FindAllAuditService findAllAuditService;

  @Mock
  private AuditRepositoryPort auditRepositoryPort;

  @Test
  void findAll() {

    var pageable = PageRequest.of(0, 2);

    Mockito.when(this.auditRepositoryPort.findAll(pageable))
        .thenReturn(new PageImpl<>(
            List.of(
                Audit
                    .builder()
                    .id(1L)
                    .path("/sarasa")
                    .method("GET")
                    .response("body")
                    .build(),
                Audit
                    .builder()
                    .id(1L)
                    .path("/sarasa")
                    .method("GET")
                    .response("body")
                    .build()
            )
        ));

    Page<Audit> audits = this.findAllAuditService.findAll(pageable);

    Assertions.assertEquals(audits.getTotalElements(), 2);
    Assertions.assertEquals(audits.getTotalPages(), 1);

  }
}