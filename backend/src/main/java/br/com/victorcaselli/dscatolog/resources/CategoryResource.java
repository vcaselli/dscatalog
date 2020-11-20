package br.com.victorcaselli.dscatolog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorcaselli.dscatolog.dto.CategoryDTO;
import br.com.victorcaselli.dscatolog.entities.Category;
import br.com.victorcaselli.dscatolog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){ 
		List<CategoryDTO> categories = service.findAll(); 
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){ 
		CategoryDTO categories = service.findById(id); 
		return ResponseEntity.ok().body(categories);
	}

}
