package dev.sch39.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select id, price from variants where id in ?1 and is_deleted=false", nativeQuery = true)
    List<Object[]> findPricesByVariantIds(List<Long> variantIds);

    @Query(value = """
            select v from VariantEntity v
            join v.product p
            join p.category c
             where (:variantName is null or v.name ilike %:variantName% and v.deleted=false and v.stock>0)
                    and (:productName is null or p.name ilike %:productName% and p.deleted=false)
                    and (:categoryName is null or c.name ilike %:categoryName% and c.deleted=false)
            """)
    Page<VariantEntity> searchVariants(
            @Param("variantName") String variantName,
            @Param("productName") String productName,
            @Param("categoryName") String categoryName,
            Pageable pageable);

    @Query(value = "select id, stock from variants where id in ?1 and is_deleted=false", nativeQuery = true)
    List<Object[]> findStocksByVariantIds(List<Long> variantIds);

    @Modifying
    @Query(value = """
            update
                variants
            set
                stock = stock - data_updated.quantity
            from
                (
                    select
                        unnest(?1) as id,
                        unnest(?2) as quantity
                ) as data_updated
            where
                variants.id = data_updated.id
                                """, nativeQuery = true)
    void updateStocks(Long[] variantIds, Double[] quantities);
}
