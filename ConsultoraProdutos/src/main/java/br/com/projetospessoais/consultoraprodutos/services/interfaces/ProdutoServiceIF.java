package br.com.projetospessoais.consultoraprodutos.services.interfaces;

import br.com.projetospessoais.consultoraprodutos.model.Produto;
import java.util.List;

/**
 *
 * @author lisa <elysangeladesouza@gmail.com>
 */
public interface ProdutoServiceIF {
    
    public List<Produto> findAll();

    public void save(Produto produto);

    public void delete(Produto produtoSelecionado);
    
}
