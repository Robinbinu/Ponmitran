package com.shopping.cart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart() {
		super();
	}

	public Cart(Long id, Product product, int quantity, User user) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.user = user;
	}

	public Cart(Product product, int quantity, User user) 
	{
		this.product=product;
		this.quantity=quantity;
		this.user=user;
		
	}

	
	public Cart(User user, Product product, int quantity) {
		this.user=user;
		this.product=product;
		this.quantity=quantity;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", product=" + product + ", quantity=" + quantity + ", user=" + user + "]";
	}

    
    
}
