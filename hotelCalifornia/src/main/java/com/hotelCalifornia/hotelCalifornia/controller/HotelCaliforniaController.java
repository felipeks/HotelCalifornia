package com.hotelCalifornia.hotelCalifornia.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelCalifornia.hotelCalifornia.model.HotelCalifornia;
import com.hotelCalifornia.hotelCalifornia.repository.HotelCaliforniaRepository;

@RestController
@RequestMapping({"/hotelcalifornia"})
public class HotelCaliforniaController {

	@Autowired
	private HotelCaliforniaRepository repository;

	@GetMapping
	 public List findAll() {
		 return repository.findAll();
	 }
	
	@GetMapping(value = "{id}")
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	} 
	
	@PostMapping
	 public HotelCalifornia create(@RequestBody HotelCalifornia hotelCalifornia) {
		return repository.save(hotelCalifornia);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity update (@PathVariable ("id") long id, @RequestBody HotelCalifornia hotelCalifornia) {
		return repository.findById(id).map(record -> {
			record.setName(hotelCalifornia.getName());
			record.setCapacity(hotelCalifornia.getCapacity());
			record.setLocation(hotelCalifornia.getLocation());
			HotelCalifornia update = repository.save(record);
			return ResponseEntity.ok().body(update);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ="/{id}")
	public ResponseEntity <?> delete (@PathVariable long id){
		return repository.findById(id).map(record -> {repository.deleteById(id);
		return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
