package br.com.projetospessoas.consultoraprodutos.controllers;

import br.com.projetospessoas.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoas.consultoraprodutos.model.Cliente;
import br.com.projetospessoas.consultoraprodutos.services.ClienteService;
import br.com.projetospessoas.consultoraprodutos.util.jsf.FacesUtil;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author elisangela
 */
@Model
public class ClienteBean {
    
    @Inject
    FacesUtil facesUtil;

    @Inject
    private Cliente cliente;

    @Inject
    private ClienteService clienteService;

    @Inject
    private Cliente clienteSelecionado;

    private List<Cliente> clientes;

    public List<Cliente> getClientes() {
        this.clientes = clienteService.findAll();
        return clientes;
    }

    public void salvar() throws ConsultoraException {
        this.clienteService.save(cliente);
        cliente = new Cliente();
        facesUtil.mensagemSucesso("Médico cadastrado com sucesso!");
    }

    public void excluir() throws ConsultoraException {
        this.clienteService.delete(clienteSelecionado);
        facesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
}
