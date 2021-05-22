package com.piyush.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

	Movie findByName(String name);
	
	Movie findById(int id);

	

}
