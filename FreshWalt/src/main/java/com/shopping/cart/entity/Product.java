package com.shopping.cart.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long product_id;
	@Column(nullable=false)
	private String product_name;
	@Column(nullable=false)
	private String product_url;
	@Column(nullable=false)
	private double product_price;
	@Column(nullable=false)
	private String category;
	@Column(nullable=false)
	private String quantity;
	
	
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_url() {
		return product_url;
	}
	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Product()
	{
		
	}
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_url=" + product_url
				+ ", product_price=" + product_price + ", category=" + category + ", quantity=" + quantity + "]";
	}
	public Product(long product_id, String product_name, String product_url, double product_price, String category,
			String quantity) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_url = product_url;
		this.product_price = product_price;
		this.category = category;
		this.quantity = quantity;
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
