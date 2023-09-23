package com.github.lucspb.pdv.repository;

import com.github.lucspb.pdv.model.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {
}
