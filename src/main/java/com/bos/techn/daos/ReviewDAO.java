package com.bos.techn.daos;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.bos.techn.beans.*;

public interface ReviewDAO extends JpaRepository<Review, UUID>{
	 @Query("""
	           select r
	           from Review r
	           where r.reviewUser.id_user = ?1
	            """)
	Optional<Review> findReviewByUser(UUID userid);
}
