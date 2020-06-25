package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
