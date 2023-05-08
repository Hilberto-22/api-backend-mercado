package br.com.backend.apibackendmercado.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.backend.apibackendmercado.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    
}
