package br.com.victorcaselli.dscatolog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcaselli.dscatolog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	


}
