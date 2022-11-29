package br.com.backend.apibackendmercado.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.apibackendmercado.dto.ProdutoDto;
import br.com.backend.apibackendmercado.model.Produto;
import br.com.backend.apibackendmercado.model.exception.ResourceNotFoundException;
import br.com.backend.apibackendmercado.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**metodo para buscar no repository a lista de produtos
     * 
     * @return lista de todos os produtos
     */
    public List<ProdutoDto> listarTodos() {
        
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(produto -> new ModelMapper()
        .map(produto,ProdutoDto.class)).collect(Collectors.toList());
    }
    
    /** metodo para buscar pelo id
     * 
     * @param id
     * @return lista produto pelo id
     */
    public Optional<ProdutoDto> listarPorId(UUID id){
        
        Optional<Produto> produto =  produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id " + id + " não encontrado");
        }
        ProdutoDto produtoDto = new ModelMapper().map(produto.get(), ProdutoDto.class);
        return Optional.of(produtoDto);
    }

    /**metodo para cadastrar um novo produto
     * 
     * @param produto
     * @return 
     */
    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto){
        produtoDto.setId(null);

        //objeto de mapeamento
        ModelMapper model = new ModelMapper();

        //conversao de um produtoDto para um produto
        Produto produto = model.map(produtoDto, Produto.class);
        
        //salva no banco com um novo Id
        produto = produtoRepository.save(produto);
        
        produtoDto.setId(produto.getId());
        return produtoDto;
    }

    /**metodo para deletar um produto pelo id
     * 
     * @param id
     */
    public void deletarProduto(UUID id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    /**metodo para atualizar um produto no banco de dados
     * 
     * @param id
     * @param produto
     * @return seta o id no produto e solicita ao produto repository para atualizar no Bd
     */
    public ProdutoDto updateProduto(UUID id, ProdutoDto produtoDto){
        
        produtoDto.setId(id);
        
        ModelMapper modelMapper = new ModelMapper();
        Produto produto = modelMapper.map(produtoDto, Produto.class);
        produtoRepository.save(produto);
        return produtoDto;
    }
}
