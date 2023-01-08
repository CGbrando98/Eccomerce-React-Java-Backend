package com.bos.techn.daos;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import com.bos.techn.beans.*;

public interface OrderDAO extends JpaRepository<Order, UUID>{
	 @Query("""
	           select o
	           from Order o
	           where o.user.id_user = ?1
	            """)
	List<Order> findAllByUserId(UUID userid);
}

