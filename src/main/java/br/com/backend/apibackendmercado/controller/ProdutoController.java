package br.com.backend.apibackendmercado.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.apibackendmercado.model.Produto;
import br.com.backend.apibackendmercado.services.ProdutoService;

@RestController()
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    /**
     * 
     * @return
     */
    @GetMapping("/listarTodos")
    public List<Produto> listarTodos(){
        return produtoService.listarTodos();
    }

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<Optional<Produto>> listarPorId(@PathVariable UUID id){
       return new ResponseEntity<>(produtoService.listarPorId(id), HttpStatus.OK);
    } 

    /**
     * 
     * @param produto
     * @return
     */
    @PostMapping("/cadastrarProduto")
    public Produto cadastrarProduto(@RequestBody Produto produto){
        return produtoService.cadastrarProduto(produto);
    }

    /**
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        produtoService.deletarProduto(id);
        return "Produto com id: " + id + " foi deletado com sucesso";
    }
    
    /**
     * 
     * @param produto
     * @param id
     * @return
     */
    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable(value = "id") UUID id, @RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.updateProduto(id, produto), HttpStatus.OK);
    }
}
 