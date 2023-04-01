package com.example.kennedynol.linguagens.api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {
    @Autowired
    private  LinguagemRepository repositorio; 
    
    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens(){
        List<Linguagem> linguagens = repositorio.findAll(Sort.by(Sort.Direction.ASC,"rank"));
        return linguagens;
    }
    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
        Linguagem novaLinguagem =  repositorio.save(linguagem);
        return new ResponseEntity<>(linguagem, HttpStatus.CREATED);
    }
    @GetMapping("/linguagens/{id}")
    public ResponseEntity<Linguagem> obterID(@PathVariable String id){
            
        Linguagem novaLinguagem =  repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(novaLinguagem, HttpStatus.ACCEPTED);
    }
    @PutMapping("/linguagens/{id}")
    public ResponseEntity<Linguagem> atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem nova){
        if(!repositorio.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } 
        nova.setId(id);
        Linguagem novaLinguagem = repositorio.save(nova);
        return new ResponseEntity<>(novaLinguagem, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/linguagens/{id}")
    public void excluirLinguagem(@PathVariable String id){
        repositorio.deleteById(id);
    }
}
