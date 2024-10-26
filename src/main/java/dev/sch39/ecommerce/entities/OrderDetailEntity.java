package dev.sch39.ecommerce.entities;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
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

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "header_id", insertable = false, updatable = false)
  private OrderHeaderEntity header;

  @Column(name = "header_id")
  private Long headerId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "variant_id", insertable = false, updatable = false)
  private VariantEntity variant;

  @Column(name = "variant_id")
  private Long variantId;

  private Double quantity;

  private Double price;

  @Column(name = "is_deleted")
  private boolean deleted;
}
