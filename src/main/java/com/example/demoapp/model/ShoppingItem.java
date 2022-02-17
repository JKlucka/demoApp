package com.example.demoapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ShoppingItem",
    indexes = {
    @Index(columnList = "CategoryId", name = "IXFK_ShoppingItem_ItemCategory"),
    @Index(columnList = "ShoppingCartId", name = "IXFK_ShoppingItem_ShoppingCart")
})
public class ShoppingItem implements Serializable, Comparable<ShoppingItem> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name ="Name", nullable = false)
  private String itemName;

  @Column(name = "Details", length = 1000)
  private String details;

  @Column(name = "Quantity")
  @ColumnDefault("0")
  private Integer quantity;

  @Column(name = "IsBought", nullable = false)
  @ColumnDefault("0")
  private Boolean isBought;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CategoryId", foreignKey = @ForeignKey(name = "FK_ShoppingItem_ItemCategory"))
  private ItemCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ShoppingCartId", nullable = false, foreignKey = @ForeignKey(name = "FK_ShoppingItem_ShoppingCart"))
  private ShoppingCart shoppingCart;

  @Override
  public int compareTo(ShoppingItem that) {
    return this.getItemName().compareTo(that.getItemName());
  }
}
