package com.bos.techn.daos;

import org.springframework.data.jpa.repository.*;

import com.bos.techn.beans.*;

public interface ShippingAddressDAO extends JpaRepository<ShippingAddress, Integer>{
}

