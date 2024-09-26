package com.fatecrl.pagamentosapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatecrl.pagamentosapi.model.Pagamento;

@Service
public class PagamentoService {

    private static List<Pagamento> pagamentos = new ArrayList<Pagamento>();

    private void pagamentoTest() {
        Pagamento pagamentoTest = new Pagamento();
        pagamentoTest.setId(1L);
        pagamentoTest.setIdUsuario(1);
        pagamentoTest.setFormaPagamento("PIX");
        pagamentoTest.setValor(280.00);
        pagamentoTest.setStatus("aprovado");
        pagamentoTest.setDataPagamento(new Date());
    }

    public PagamentoService() {
        pagamentoTest();
    }

    public List<Pagamento> findAll(){
        return pagamentos;
    }

    public Pagamento find(Pagamento pagamento){
        return pagamentos.stream()
                     .filter(c -> c.equals(pagamento))
                     .findFirst().orElse(null);                           
    }

    public Pagamento find(Long id){
        return find(new Pagamento(id));
    }

    public List<Pagamento> findByidUsuario(int idUsuario){
        return pagamentos.stream()
                    .filter(c -> c.getIdUsuario() == idUsuario)
                    .toList();
    }

    public void create(Pagamento pagamento){
        Long newId = (long) (pagamentos.size() + 1);
        pagamento.setId(newId);
        pagamentos.add(pagamento);
    }
    
    public Boolean delete(Long id){
        Pagamento _pagamento = find(id);
        if (_pagamento != null){
            pagamentos.remove(_pagamento);
            return true;
        }
        return false;
    }

    public Boolean update(Pagamento pagamento) {
        Pagamento _pagamento = find(pagamento);
        if (_pagamento != null) { 
            if (pagamento.getIdUsuario() > 0) {
                _pagamento.setIdUsuario(pagamento.getIdUsuario());
            }
            if (pagamento.getValor() != null && pagamento.getValor() > 0) {
                _pagamento.setValor(pagamento.getValor());
            }
            if (pagamento.getFormaPagamento() != null && !pagamento.getFormaPagamento().isBlank()) {
                _pagamento.setFormaPagamento(pagamento.getFormaPagamento());
            }
            if (pagamento.getStatus() != null && !pagamento.getStatus().isBlank()) {
                _pagamento.setStatus(pagamento.getStatus());
            }
            _pagamento.setDataPagamento(new Date());

            return true;
        }
        return false;
    }
}
