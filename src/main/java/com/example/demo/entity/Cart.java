package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart implements Serializable {
	private static final long serialVersionUID = 4887904943282174032L;
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cart")
	private Set<ProductInOrder> products = new HashSet<>();

	@Override
	public String toString() {
		return "Cart{" + "cartId=" + cartId + ", products=" + products + '}';
	}

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JsonIgnore
	private User user;

	public Cart(User user) {
		this.user = user;
	}
}
