package br.com.projetospessoais.consultoraprodutos.controllers;

import br.com.projetospessoais.consultoraprodutos.exceptions.NegocioException;
import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import br.com.projetospessoais.consultoraprodutos.services.interfaces.ClienteServiceIF;
import br.com.projetospessoais.consultoraprodutos.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
@Model
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Cliente cliente;

    @Inject
    private ClienteServiceIF clienteService;

    @Inject
    private Cliente clienteSelecionado;

    private transient List<Cliente> clientes;

    public ClienteBean() {
    }
    
    @PostConstruct
    public void init() {
        this.clientes = clienteService.findAll();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void salvar() {
        this.clienteService.save(cliente);
        if (getEditando()) {
            FacesUtil.mensagemSucesso("Cadastro de cliente '" + cliente.getNome() + "' atualizado com sucesso!");
        } else {
            FacesUtil.mensagemSucesso("Cadastro efetuado com sucesso!");
        }
        FacesUtil.redirecionaPara("PesquisaCliente.xhtml");
        cliente = new Cliente();
    }

    public void excluir() throws NegocioException {
        this.clienteService.delete(clienteSelecionado);
        FacesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
        FacesUtil.redirecionaPara("PesquisaCliente.xhtml");
    }
    
    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro e retorna true, senao refere-se a um produto
     * a ser editado, retornando false.
     *
     * @return
     */
    public boolean getEditando() {
        return this.cliente.getId() != null;
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

    public ClienteServiceIF getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteServiceIF clienteService) {
        this.clienteService = clienteService;
    }
}
