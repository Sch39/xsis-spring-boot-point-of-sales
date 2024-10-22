package dev.sch39.ecommerce.entities;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "order_details")
@SQLDelete(sql = "update order_headers set is_deleted=true where id=?")
public class OrderDetailEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long orderDetailId;

  @ManyToOne
  @JoinColumn(name = "header_id", insertable = false, updatable = false)
  private OrderDetailEntity orderDetail;

  @Column(name = "is_deleted")
  private boolean deleted;
}
