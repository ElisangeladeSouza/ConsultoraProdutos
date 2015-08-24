package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.ProdutoDao;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Produto;
import br.com.projetospessoais.consultoraprodutos.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class ProdutoService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ProdutoDao produtoDao;

    public ProdutoService() {
    }
    
    @Transactional
    public void save(Produto produto) {
        if (produto != null) {
            this.produtoDao.salvar(produto);
        }
    }

    @Transactional
    public void delete(Produto produto)  throws ConsultoraException{
        produtoDao.delete(findById(produto.getId()));
    }
    
    public Produto findById(Long id) {
        return produtoDao.findById(id);
    }

    public List<Produto> findAll() {
        return produtoDao.findAll();
    }
    
}
