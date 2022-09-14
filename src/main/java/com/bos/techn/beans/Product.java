package com.bos.techn.beans;

import java.util.*;

import javax.persistence.*;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String image;
	private String description;
	private String brand;
	private String category;
	private double price;
	private int stock;
	private double rating;
	private int reviews;
	public Product(int id, String name, String image, String description, String brand, String category, double price,
			int stock, double rating, int reviews) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.rating = rating;
		this.reviews = reviews;
	}
	public Product(String name, String image, String description, String brand, String category, double price,
			int stock, double rating, int reviews) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.rating = rating;
		this.reviews = reviews;
	}
	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", brand="
				+ brand + ", category=" + category + ", price=" + price + ", stock=" + stock + ", rating=" + rating
				+ ", reviews=" + reviews + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(brand, category, description, id, image, name, price, rating, reviews, stock);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(category, other.category)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating) && reviews == other.reviews
				&& stock == other.stock;
	}
	
	
	
	
}
