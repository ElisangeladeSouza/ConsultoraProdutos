package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.ContaDao;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Conta;
import br.com.projetospessoais.consultoraprodutos.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class ContaService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ContaDao loginDao;

    public ContaService() {
    }
    
    @Transactional
    public void save(Conta login) {
        if (login != null) {
            this.loginDao.salvar(login);
        }
    }
    
    @Transactional
    public void delete(Conta login) throws ConsultoraException {
        loginDao.delete(findById(login.getId()));
    }
    
    public Conta findById(Long id) {
        return loginDao.findById(id);
    }
    
    public List<Conta> findAll() {
        return loginDao.findAll();
    }

}