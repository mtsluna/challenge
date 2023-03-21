package com.tenpo.challenge.audit.adapters.outbound.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditEntity {

  @Id
  @Column(name = "audit_id")
  @GeneratedValue(generator = "audit_id_seq")
  @SequenceGenerator(
      name = "audit_id_seq",
      sequenceName = "audit_id_seq",
      allocationSize = 1,
      schema = "public"
  )
  private Long id;

  @Column
  private String method;

  @Column
  private String path;

  @JdbcTypeCode(SqlTypes.JSON)
  private Object response;
}
