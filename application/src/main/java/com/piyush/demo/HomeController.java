package com.piyush.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	MovieRepo movieRepo;

	@PostMapping("/addSeries")
	public void addSeries(@RequestBody Movie movie) {
	
			movieRepo.save(movie);
			System.out.println(movie.getRating());
	}
	
	@GetMapping("/series")
	public List<Movie> allSeries() {
		return movieRepo.findAll();
	}
	
	@GetMapping("/series/id/{id}")
	public Movie getById(@PathVariable int id) {
		return movieRepo.findById(id);
	}
	
	@GetMapping("/series/name/{name}")
	public Movie getByName(@PathVariable String name) {
		return movieRepo.findByName(name);
	}
	
	@PutMapping("/update")
	public void updateMovie(@RequestBody Movie movie){
		movieRepo.save(movie);
	}
	@DeleteMapping(path = "/delete/{id}")
	public String deleteById(@PathVariable int id) {
		movieRepo.deleteById(id);
		return "Series Removed ! "+id;
	}
	
	
	
	
}
