package com.tenpo.challenge.percentage.adapters.outbound.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercentageEntity {

  @Id
  @Column(name = "percentage_id")
  @GeneratedValue(generator = "percentage_id_seq")
  @SequenceGenerator(
      name = "percentage_id_seq",
      sequenceName = "percentage_id_seq",
      allocationSize = 1,
      schema = "public"
  )
  private Long id;

  private Double percentage;
}
