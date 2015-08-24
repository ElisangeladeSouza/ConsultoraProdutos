package br.com.projetospessoais.consultoraprodutos.dao;

import br.com.projetospessoais.consultoraprodutos.model.Produto;
import java.io.Serializable;

/**
 *
 * @author elisangela
 */
public class ProdutoDao extends DaoAbstract<Produto> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public ProdutoDao() {
        super(Produto.class);
    }
    
}
