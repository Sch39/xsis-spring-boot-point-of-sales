package dev.sch39.ecommerce.dtos.rest.response;

import dev.sch39.ecommerce.entities.WalletEntity;
import lombok.Data;

@Data
public class RestWalletUserResponseDto {
  private Long id;
  private Double balance;
  private Long userId;

  public RestWalletUserResponseDto(WalletEntity walletEntity) {
    this.id = walletEntity.getId();
    this.balance = walletEntity.getBalance();
    this.userId = walletEntity.getUserId();
  }
}
