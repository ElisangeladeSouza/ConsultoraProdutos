package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.ProdutoDao;
import br.com.projetospessoais.consultoraprodutos.exceptions.NegocioException;
import br.com.projetospessoais.consultoraprodutos.model.Produto;
import br.com.projetospessoais.consultoraprodutos.services.interfaces.ProdutoServiceIF;
import br.com.projetospessoais.consultoraprodutos.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class ProdutoService implements ProdutoServiceIF, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ProdutoDao produtoDao;

    public ProdutoService() {
    }
    
    @Transactional
    @Override
    public void save(Produto produto) {
        if (produto != null) {
            this.produtoDao.salvar(produto);
        }
    }

    /**
     *
     * @param produto
     * @throws NegocioException
     */
    @Transactional
    @Override
    public void delete(Produto produto)  throws NegocioException {
        produtoDao.delete(findById(produto.getId()));
    }
    
    public Produto findById(Long id) {
        return produtoDao.findById(id);
    }

    @Override
    public List<Produto> findAll() {
        return produtoDao.findAll();
    }
    
}
