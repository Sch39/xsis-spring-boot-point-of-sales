package dev.sch39.ecommerce.entities;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "order_headers")
@SQLDelete(sql = "update order_headers set is_deleted=true where id=?")
public class OrderHeaderEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String reference;
  private Double amount;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity user;

  @Column(name = "user_id")
  private Long userId;

  @Enumerated(EnumType.STRING)
  private OrderHeaderStatus orderStatus = OrderHeaderStatus.PENDING;

  @Column(name = "is_deleted")
  private boolean deleted = false;

  public enum OrderHeaderStatus {
    PENDING,
    SUCCESS,
    FAILED;
  }
}
