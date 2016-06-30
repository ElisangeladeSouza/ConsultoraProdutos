package br.com.projetospessoais.consultoraprodutos.services.interfaces;

import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import java.util.List;

/**
 *
 * @author lisa <elysangeladesouza@gmail.com>
 */
public interface ClienteServiceIF {
    
    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public void delete(Cliente clienteSelecionado);
    
}
