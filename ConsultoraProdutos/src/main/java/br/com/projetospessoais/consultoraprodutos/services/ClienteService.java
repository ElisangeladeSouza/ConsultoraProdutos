package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.ClienteDao;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class ClienteService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ClienteDao clienteDao;

    public ClienteService() {
    }
    
    @Transactional
    public void save(Cliente cliente) {
        if (cliente != null) {
            this.clienteDao.salvar(cliente);
        }
    }

    @Transactional
    public void delete(Cliente cliente)  throws ConsultoraException{
        clienteDao.delete(findById(cliente.getId()));
    }
    
    public Cliente findById(Long id) {
        return clienteDao.findById(id);
    }

    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }
    
}
