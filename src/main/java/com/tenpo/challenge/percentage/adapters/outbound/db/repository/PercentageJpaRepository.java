package com.tenpo.challenge.percentage.adapters.outbound.db.repository;

import com.tenpo.challenge.percentage.adapters.outbound.db.entity.PercentageEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PercentageJpaRepository extends JpaRepository<PercentageEntity, Long> {

  @Query(value = "SELECT * FROM percentage_entity p ORDER BY p.percentage_id DESC LIMIT 1", nativeQuery = true)
  Optional<PercentageEntity> findLastPercentage();

}
