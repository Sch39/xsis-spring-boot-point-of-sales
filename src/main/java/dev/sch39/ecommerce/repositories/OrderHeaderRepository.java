package dev.sch39.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sch39.ecommerce.entities.OrderHeaderEntity;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeaderEntity, Long> {
  @Query(value = "select count(id) from order_headers where date_part('year', created_at)=?1 and date_part('month', created_at)=?2", nativeQuery = true)
  int countOrdersInMonth(int year, int month);
}
