package com.green.base.pojo.ecom.shopping;

import java.io.Serializable;

import com.green.base.entity.product.Product;
/**
 * 
 * @author gaurav
 *
 * Class is responsible for holding items selected by user while shopping
 */
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2211921429409691746L;
	
	
	private Product product; 
	private Integer quantity;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
