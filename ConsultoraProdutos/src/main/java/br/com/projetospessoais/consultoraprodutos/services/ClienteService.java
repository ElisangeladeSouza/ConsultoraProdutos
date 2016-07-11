package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.ClienteDao;
import br.com.projetospessoais.consultoraprodutos.exceptions.NegocioException;
import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import br.com.projetospessoais.consultoraprodutos.services.interfaces.ClienteServiceIF;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import br.com.projetospessoais.consultoraprodutos.util.jpa.Transactional;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class ClienteService implements ClienteServiceIF, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ClienteDao clienteDao;

    public ClienteService() {
    }
    
    @Transactional
    @Override
    public void save(Cliente cliente) {
        if (cliente != null) {
            this.clienteDao.salvar(cliente);
        }
    }
    
    /**
     *
     * @param cliente 
     * @throws NegocioException
     */
    @Transactional
    @Override
    public void delete(Cliente cliente)  throws NegocioException{
        clienteDao.delete(findById(cliente.getId()));
    }
    
    public Cliente findById(Long id) {
        return clienteDao.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }
    
}
