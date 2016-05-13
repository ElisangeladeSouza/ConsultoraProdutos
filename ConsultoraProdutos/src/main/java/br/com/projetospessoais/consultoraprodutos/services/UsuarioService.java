package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.UsuarioDao;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Usuario;
import br.com.projetospessoais.consultoraprodutos.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class UsuarioService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private UsuarioDao usuarioDao;

    public UsuarioService() {
    }
    
    @Transactional
    public void save(Usuario usuario) {
        if (usuario != null) {
            this.usuarioDao.salvar(usuario);
        }
    }
    
    @Transactional
    public void delete(Usuario usuario) throws ConsultoraException {
        usuarioDao.delete(findById(usuario.getId()));
    }
    
    public Usuario findById(Long id) {
        return usuarioDao.findById(id);
    }
    
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

}