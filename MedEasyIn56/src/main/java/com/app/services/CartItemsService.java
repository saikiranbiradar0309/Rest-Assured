package com.app.services;

import java.util.Set;

import com.app.dto.CartItemDTO;
import com.app.entities.CartItems;
import com.app.entities.Carts;
import com.app.entities.Products;

public interface CartItemsService {

	CartItems addToCart(CartItemDTO cartItem);

	Set<CartItems> getCartItems(Long CartId);

	void DeleteCartItemsFromCart(Carts cartId);

	void deleteByProductId(Products product);

	void deleteItem(Long cartItemId);

	CartItems updateItem(Long cartItemId);

	CartItems getCartitem(Long CartId);

}
