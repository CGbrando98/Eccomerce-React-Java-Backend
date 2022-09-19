package com.bos.techn.services;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;

public interface UserServices {
	void saveUser(User user);
	User getUser(int id) throws UserNotFoundException;
	void deleteUser(int id) throws UserNotFoundException;
}
