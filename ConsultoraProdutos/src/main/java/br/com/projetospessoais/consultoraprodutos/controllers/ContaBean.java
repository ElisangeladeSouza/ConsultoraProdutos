package br.com.projetospessoais.consultoraprodutos.controllers;

import br.com.projetospessoais.consultoraprodutos.enumerations.Permissao;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Conta;
import br.com.projetospessoais.consultoraprodutos.services.ContaService;
import br.com.projetospessoais.consultoraprodutos.util.jsf.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
@Model
public class ContaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Conta conta;

    @Inject
    private ContaService contaService;
    
    @Inject
    private Conta contaSelecionado;

    private transient List<Conta> contas;
    
    private final transient List<Permissao> permissoes;

    private String confereSenha;
    

    public ContaBean() {
        permissoes = Arrays.asList(Permissao.values());
    }

    public List<Conta> getContas() {
        this.contas = contaService.findAll();
        return contas;
    }
    
    public List<Permissao> getPermissao() {
        return permissoes;
    }

    public void salvar() throws ConsultoraException {
        if (conta.getSenha() == null ? confereSenha == null : conta.getSenha().equals(confereSenha)) {
            this.contaService.save(conta);
            if (getEditando()) {
                FacesUtil.mensagemSucesso("Cadastro de usuário '" + conta.getId() + "' atualizado com sucesso!");
                FacesUtil.redirecionaPara("PesquisaLogin.xhtml");
            } else {
                FacesUtil.mensagemSucesso("Cadastro efetuado com sucesso!");
            }
            conta = new Conta();
        } else {
            FacesUtil.mensagemAviso("Senhas não conferem!");
        }

    }

    public void excluir() throws ConsultoraException {
        this.contaService.delete(contaSelecionado);
        FacesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
    }

    public void login() throws IOException {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(conta.getUsuario(), conta.getSenha()));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : "/ConsultoraProdutos/Home.xhtml");
        } catch (AuthenticationException e) {
            FacesUtil.mensagemErro("Falha no login");
            System.out.println(e);
        }
    }
    
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "/Login.xhtml?faces-redirect=true";
    }
    
    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     *
     * @return
     */
    public boolean getEditando() {
        return this.conta.getId() != null;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public ContaService getContaService() {
        return contaService;
    }

    public void setContaService(ContaService contaService) {
        this.contaService = contaService;
    }

    public Conta getContaSelecionado() {
        return contaSelecionado;
    }

    public void setContaSelecionado(Conta contaSelecionado) {
        this.contaSelecionado = contaSelecionado;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }

}
