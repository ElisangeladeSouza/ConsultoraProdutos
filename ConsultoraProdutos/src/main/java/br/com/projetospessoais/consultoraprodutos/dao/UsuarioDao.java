package br.com.projetospessoais.consultoraprodutos.dao;

import br.com.projetospessoais.consultoraprodutos.model.Usuario;
import java.io.Serializable;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class UsuarioDao extends DaoAbstract<Usuario> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public UsuarioDao() {
        super(Usuario.class);
    }
    
}
