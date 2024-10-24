package dev.sch39.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sch39.ecommerce.entities.VariantEntity;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
  @Query(value = "select * from variants where slug=?1 and is_deleted=false", nativeQuery = true)
  Optional<VariantEntity> findBySlug(String slug);

  @Query(value = "select * from variants where is_deleted=false", nativeQuery = true)
  List<VariantEntity> findAllNotDeleted();

  @Query(value = "select * from variants where is_deleted=true", nativeQuery = true)
  List<VariantEntity> findAllDeleted();
}
