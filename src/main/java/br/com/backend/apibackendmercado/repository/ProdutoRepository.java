package br.com.backend.apibackendmercado.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.apibackendmercado.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    
}
