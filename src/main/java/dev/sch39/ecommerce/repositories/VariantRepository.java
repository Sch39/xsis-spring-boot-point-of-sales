package dev.sch39.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sch39.ecommerce.entities.VariantEntity;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
  @Query(value = "select * from variants where slug=?1 and is_deleted=false", nativeQuery = true)
  Optional<VariantEntity> findBySlug(String slug);

  @Query(value = "select * from variants where is_deleted=false", nativeQuery = true)
  Page<VariantEntity> findAllNotDeleted(Pageable pageable);

  @Query(value = "select * from variants where is_deleted=true", nativeQuery = true)
  Page<VariantEntity> findAllDeleted(Pageable pageable);

  @Query(value = """
            WITH req_data AS (
          SELECT
              req:: jsonb reqb
          FROM
              jsonb_array_elements(
                  (?1)::jsonb
              ) AS req
      )
      SELECT
          id
      FROM
          variants v
          JOIN req_data rd ON v.id = (rd.reqb -> 'variantId') :: int
      WHERE
          v.stock >= (rd.reqb -> 'quantity') :: int

      and is_deleted=false
            """, nativeQuery = true)
  List<Long> findAvailableVariantIds(String ordersJson);

  @Query(value = "select price from variants where id in ?1 and is_deleted=false", nativeQuery = true)
  List<Double> findPricesByVariantIds(List<Long> variantIds);
}
