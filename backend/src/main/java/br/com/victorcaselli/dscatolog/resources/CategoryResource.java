package br.com.victorcaselli.dscatolog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.victorcaselli.dscatolog.dto.CategoryDTO;
import br.com.victorcaselli.dscatolog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService service;
	
	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)
			{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<CategoryDTO> categories = service.findAllPaged(pageRequest); 
		
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){ 
		CategoryDTO categories = service.findById(id); 
		return ResponseEntity.ok().body(categories);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO entity){ 
		entity = service.insert(entity); 
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(entity.getId())
				.toUri();
				
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto, @PathVariable Long id){ 
		dto = service.update(id,dto); 
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){ 
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
