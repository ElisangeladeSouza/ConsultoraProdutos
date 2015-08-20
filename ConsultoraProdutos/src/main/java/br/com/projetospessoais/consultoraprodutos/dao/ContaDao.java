package br.com.projetospessoais.consultoraprodutos.dao;

import br.com.projetospessoais.consultoraprodutos.model.Conta;
import java.io.Serializable;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class ContaDao extends DaoAbstract<Conta> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public ContaDao() {
        super(Conta.class);
    }
    
}
