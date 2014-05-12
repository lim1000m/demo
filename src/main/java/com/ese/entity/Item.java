package com.ese.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Index;
import com.ese.config.validation.Eng;

/**
 * An item in an order
 */
@Entity
public class Item {

	@Index(name="id_index")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="orderId")
	private Order order;

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="proDeptId")
	private ProDept proDept;
	
	@NotNull
	@Eng
	private String product;

	@NotNull
	private int price;

	@NotNull
	private int quantity;

	private int userId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ProDept getProDept() {
		return proDept;
	}

	public void setProDept(ProDept proDept) {
		this.proDept = proDept;
	}

}
