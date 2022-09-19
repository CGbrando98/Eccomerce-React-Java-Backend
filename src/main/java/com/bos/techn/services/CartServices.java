package com.bos.techn.services;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface CartServices {
	void saveCart(Cart cart);
	Cart getCart(int id) throws CartNotFoundException;
	void deleteCart(int id) throws CartNotFoundException;
}
