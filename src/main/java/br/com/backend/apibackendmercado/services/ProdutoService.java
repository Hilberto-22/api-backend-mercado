package br.com.backend.apibackendmercado.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.apibackendmercado.model.Produto;
import br.com.backend.apibackendmercado.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**metodo para buscar no repository a lista de produtos
     * 
     * @return lista de todos os produtos
     */
    public List<Produto> listarTodos() {
        
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }
    
    /** metodo para buscar pelo id
     * 
     * @param id
     * @return lista produto pelo id
     */
    public Optional<Produto> listarPorId(UUID id){
        return produtoRepository.findById(id);
    }

    /**metodo para cadastrar um novo produto
     * 
     * @param produto
     * @return 
     */
    public Produto cadastrarProduto(Produto produto){

        produto = produtoRepository.save(produto);
        return produto;
    }

    /**metodo para deletar um produto pelo id
     * 
     * @param id
     */
    public void deletarProduto(UUID id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new RuntimeException("Não foi possível deletar o produto com o id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    /**metodo para atualizar um produto no banco de dados
     * 
     * @param id
     * @param produto
     * @return seta o id no produto e solicita ao produto repository para atualizar no Bd
     */
    public Produto updateProduto(UUID id, Produto produto){
        
        produto.setId(id);
        return produtoRepository.save(produto);
    }
}
