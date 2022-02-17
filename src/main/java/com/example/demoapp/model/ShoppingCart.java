package com.example.demoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart implements Serializable, Comparable<ShoppingCart>{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(name = "ID")
  private Long id;

  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(name = "Name", nullable = false)
  private String name;

  @ToString.Include
  @Column(name = "Details", length = 1000)
  private String details;

  @OneToMany(mappedBy = "ShoppingItems", fetch = FetchType.LAZY)
  private Set<ShoppingItem> shoppingItemsSet;

  @Override
  public int compareTo(ShoppingCart that) {
    return this.getId().compareTo(that.getId());
  }
}
