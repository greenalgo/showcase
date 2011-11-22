package com.green.base.pojo.ecom.shopping;

import java.io.Serializable;
import java.util.Collection;


/**
 * 
 * @author gaurav
 * 
 * Object behaving as shopping for customer doing online shopping
 */
public class ShoppingCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052684634035153795L;
	
	private Collection<CartItem> cartItems;

	public void setCartItems(Collection<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Collection<CartItem> getCartItems() {
		return cartItems;
	}
	
}
