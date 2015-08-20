package br.com.projetospessoais.consultoraprodutos.dao;

import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import java.io.Serializable;

/**
 *
 * @author elisangela
 */
public class ClienteDao extends DaoAbstract<Cliente> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public ClienteDao() {
        super(Cliente.class);
    }
    
}
