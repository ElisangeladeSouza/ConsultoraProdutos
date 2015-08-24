package br.com.projetospessoais.consultoraprodutos.controllers;

import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import br.com.projetospessoais.consultoraprodutos.services.ClienteService;
import br.com.projetospessoais.consultoraprodutos.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private ClienteService clienteService;

    @Inject
    private Cliente clienteSelecionado;

    private transient List<Cliente> clientes;

    public ClienteBean() {
    }

    public List<Cliente> getClientes() {
        this.clientes = clienteService.findAll();
        return clientes;
    }

    public void salvar() throws ConsultoraException {
        this.clienteService.save(cliente);
        if (getEditando()) {
            FacesUtil.mensagemSucesso("Cadastro de cliente '" + cliente.getNome() + "' atualizado com sucesso!");
            FacesUtil.redirecionaPara("PesquisaCliente.xhtml");
        } else {
            FacesUtil.mensagemSucesso("Cadastro efetuado com sucesso!");
        }
        cliente = new Cliente();
    }

    public void excluir() throws ConsultoraException {
        this.clienteService.delete(clienteSelecionado);
        FacesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
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

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
}
