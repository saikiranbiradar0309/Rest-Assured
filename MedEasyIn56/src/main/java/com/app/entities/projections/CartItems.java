package com.app.entities.projections;

public class CartItems {
	private Integer quantity;
	private double totalPrice;
	private int cartId;
	private int productId;
	
	public CartItems() {
		super();
	}
	
	public CartItems(Integer quantity, double totalPrice, int cartId, int productId) {
		super();
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.cartId = cartId;
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
	
	
	

}
/*
 package com.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItems extends BaseEntity{
	@Column(name="quantity")
	private Integer quantity;
	@Column(name = "total_price", nullable=false)
	private Double totalPrice;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Carts cartId;
	@OneToOne
	@JoinColumn(name = "product_id")
	private Products productId;
	
	public CartItems() {
		super();
		System.out.println("in ctor"+getClass().getName());
	}

	public CartItems(Integer quantity, Double totalPrice, Carts cartId, Products productId) {
		super();
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.cartId = cartId;
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Carts getCartId() {
		return cartId;
	}

	public void setCartId(Carts cartId) {
		this.cartId = cartId;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "CartItems [quantity=" + quantity + ", totalPrice=" + totalPrice + ", cartId=" + cartId + ", productId="
				+ productId + ", getId()=" + getId() + "]";
	}
	
	
	

}

 */
