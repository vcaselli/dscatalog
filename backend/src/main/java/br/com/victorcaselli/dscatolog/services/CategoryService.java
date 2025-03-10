package br.com.victorcaselli.dscatolog.services;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.victorcaselli.dscatolog.dto.CategoryDTO;
import br.com.victorcaselli.dscatolog.entities.Category;
import br.com.victorcaselli.dscatolog.repositories.CategoryRepository;
import br.com.victorcaselli.dscatolog.services.exceptions.DataBaseException;
import br.com.victorcaselli.dscatolog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){ 
		return repository.findAll(pageRequest)
				.map(x -> new CategoryDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) { 
		Optional<Category> entity = repository.findById(id);
		Category obj = entity
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(obj);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) { 
		Category obj = new Category();
		obj.setName(dto.getName());
		obj = repository.save(obj);
		return new CategoryDTO(obj);
		
	}
	
	
	public void updateData(Category obj, CategoryDTO dto) { 
		obj.setName(dto.getName());
	}
	
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) { 
		try {
		Category obj = repository.getOne(id);
		updateData(obj, dto);
		repository.save(obj);
		return new CategoryDTO(obj);
		}catch(EntityNotFoundException enfe) { 
			throw new ResourceNotFoundException("Id not found! ID: "+id);
		}
	}
	
	@Transactional
	public void delete(Long id) { 
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) { 
			throw new ResourceNotFoundException("Id not found! ID:"+id);
		}catch(DataIntegrityViolationException d) {
			throw new DataBaseException("Integrity violation");
		}
	}

}
