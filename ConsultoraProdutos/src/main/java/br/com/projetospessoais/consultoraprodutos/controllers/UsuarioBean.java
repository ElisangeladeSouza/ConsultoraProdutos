package br.com.projetospessoais.consultoraprodutos.controllers;

import br.com.projetospessoais.consultoraprodutos.enumerations.Permissao;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Usuario;
import br.com.projetospessoais.consultoraprodutos.services.UsuarioService;
import br.com.projetospessoais.consultoraprodutos.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
@Model
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuario usuario;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private Usuario usuarioSelecionado;

    private transient List<Usuario> usuarios;

    private static transient List<Permissao> permissoes = new ArrayList<>();

    private String confereSenha;

    private final String usuarioLogado;
    
    public UsuarioBean() {
        permissoes = Arrays.asList(Permissao.values());
        this.usuarioLogado = (String) SecurityUtils.getSubject().getPrincipal();
    }

    @PostConstruct
    public void init() {
        this.usuarios = usuarioService.findAll();
    }

    public void salvar() throws ConsultoraException {
        if (usuario.getPassword() == null ? confereSenha == null : usuario.getPassword().equals(confereSenha)) {
            this.usuarioService.save(usuario);
            if (getEditando()) {
                FacesUtil.mensagemSucesso("Cadastro de usuário '" + usuario.getId() + "' atualizado com sucesso!");
                FacesUtil.redirecionaPara("PesquisaUsuario.xhtml");
            } else {
                FacesUtil.mensagemSucesso("Cadastro efetuado com sucesso!");
            }
            usuario = new Usuario();
        } else {
            FacesUtil.mensagemAviso("Senhas não conferem!");
        }
    }

    public void excluir() throws ConsultoraException {
        this.usuarioService.delete(usuarioSelecionado);
        FacesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
    }

    public void login() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(usuario.getUserName(), new Sha256Hash(usuario.getPassword()).toHex());

            currentUser.login(token);

        } catch (UnknownAccountException uae) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Usuário incorreto"));

        } catch (IncorrectCredentialsException ice) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Senha incorreta"));

        } catch (LockedAccountException lae) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Usuário está bloqueado"));

        } catch (AuthenticationException aex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", aex.toString()));
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
        return this.usuario.getId() != null;
    }
    
    public void authorizedUserControl() {
        if (null != SecurityUtils.getSubject().getPrincipal()) {
            NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "Home.xhtml?faces-redirect=true");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public static List<Permissao> getPermissoes() {
        return permissoes;
    }

    public static void setPermissoes(List<Permissao> permissoes) {
        UsuarioBean.permissoes = permissoes;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }
    
    public String getUsuarioLogado() {
        return usuarioLogado;
    }

}
