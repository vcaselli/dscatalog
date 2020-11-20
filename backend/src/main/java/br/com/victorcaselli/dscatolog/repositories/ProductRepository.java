package br.com.victorcaselli.dscatolog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victorcaselli.dscatolog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	


}
