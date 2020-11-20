package br.com.victorcaselli.dscatolog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victorcaselli.dscatolog.entities.Category;
import br.com.victorcaselli.dscatolog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository repository;
	
	
	public List<Category> findAll(){ 
		return repository.findAll();
	}

}
