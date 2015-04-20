package br.com.projetospessoas.consultoraprodutos.services;

import br.com.projetospessoas.consultoraprodutos.dao.ClienteDao;
import br.com.projetospessoas.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoas.consultoraprodutos.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author elisangela
 */
public class ClienteService implements Serializable {
    
    @Inject
    private ClienteDao clienteDao;

    @Transactional
    public void save(Cliente cliente) throws ConsultoraException {
        this.clienteDao.salvar(cliente);
    }

    @Transactional
    public void delete(Cliente cliente)  throws ConsultoraException{
        cliente = findById(cliente.getId());
        clienteDao.delete(cliente);
    }

    public Cliente findById(Long id) throws ConsultoraException {
        return clienteDao.findById(id);
    }

    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }
    
}
