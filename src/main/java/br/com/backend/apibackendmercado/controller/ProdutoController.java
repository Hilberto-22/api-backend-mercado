package br.com.backend.apibackendmercado.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import br.com.backend.apibackendmercado.dto.ProdutoDto;
import br.com.backend.apibackendmercado.model.ProdutoRequest;
import br.com.backend.apibackendmercado.model.ProdutoResponse;
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
    public ResponseEntity<List<ProdutoResponse>> listarTodos(){
        List<ProdutoDto> produtos =  produtoService.listarTodos();
        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtos.stream()
                .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> listarPorId(@PathVariable(value = "id") UUID id){
        
        Optional<ProdutoDto> produtoDto = produtoService.listarPorId(id);
        ProdutoResponse produto = new ModelMapper().map(produtoDto.get(), ProdutoResponse.class);

        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    } 

    /**
     * 
     * @param produto
     * @return
     */
    @PostMapping("/cadastrarProduto")
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest){

        ModelMapper model = new ModelMapper();

        ProdutoDto produtoDto = model.map(produtoRequest, ProdutoDto.class);
        produtoDto = produtoService.cadastrarProduto(produtoDto);

        return new ResponseEntity<>(model.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);
    }

    /**
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * 
     * @param produto
     * @param id
     * @return
     */
    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<ProdutoResponse> updateProduto(@PathVariable(value = "id") UUID id, @RequestBody ProdutoRequest produtoRequest){
       
        ModelMapper modelMapper = new ModelMapper();
        ProdutoDto produtodDto = modelMapper.map(produtoRequest, ProdutoDto.class);

        produtodDto = produtoService.updateProduto(id, produtodDto);
        return new ResponseEntity<>(modelMapper.map(produtodDto, ProdutoResponse.class), HttpStatus.OK);
    }
}
 