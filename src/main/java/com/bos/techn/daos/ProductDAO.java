package com.bos.techn.daos;
import java.util.*;

import org.springframework.data.jpa.repository.*;
import com.bos.techn.beans.*;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	List<Product> findByNameLike(String keyword);
	List<Product> findTop3ByOrderByAvgratingAsc();
}
