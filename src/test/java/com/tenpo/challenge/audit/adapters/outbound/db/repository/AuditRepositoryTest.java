package com.tenpo.challenge.audit.adapters.outbound.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.tenpo.challenge.audit.domain.model.Audit;
import com.tenpo.challenge.calculation.adapters.inbound.api.presentation.CalculationPresentation;
import com.tenpo.challenge.percentage.domain.model.Percentage;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({AuditRepository.class})
@ActiveProfiles("test")
class AuditRepositoryTest {

  @Autowired
  private AuditRepository auditRepository;

  @Test
  void save() {

    var audit = Audit
        .builder()
        .path("/sarasa")
        .method("GET")
        .response("body")
        .build();

    Assertions.assertDoesNotThrow(() -> {
      Audit result = this.auditRepository.save(audit);

      Assertions.assertNotNull(result);
      Assertions.assertNotNull(result.getId());
    });

  }

  @Test
  @Sql("/scripts/audit/find-all.sql")
  void findAll() {

    var pageable = PageRequest.of(0, 3);

    Page<Audit> page = this.auditRepository.findAll(pageable);

    Assertions.assertNotNull(page.getContent());
    Assertions.assertEquals(3, page.getContent().size());

  }
}