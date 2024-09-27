package com.fatecrl.pagamentosapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.pagamentosapi.model.Pagamento;
import com.fatecrl.pagamentosapi.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pagamento> create(@RequestBody Pagamento pagamento){
        pagamentoService.create(pagamento);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(pagamento.getId())
                            .toUri();
        return ResponseEntity.created(location).body(pagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> get(@PathVariable("id") Long id){
        Pagamento pagamento = pagamentoService.find(id);
        if (pagamento != null){
            return ResponseEntity.ok(pagamento);
        }
        return ResponseEntity.notFound().build();
    }    

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pagamento>> getByIdUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Pagamento> pagamentos = pagamentoService.findByidUsuario(idUsuario);

    if (!pagamentos.isEmpty()) {
        return ResponseEntity.ok(pagamentos);
    }
    
    return ResponseEntity.notFound().build();
}

@DeleteMapping("/{id}")
    public ResponseEntity<Pagamento> delete(@PathVariable("id") Long id){
        if (pagamentoService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
