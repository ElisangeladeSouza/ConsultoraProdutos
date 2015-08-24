package br.com.projetospessoais.consultoraprodutos.controllers;

import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Produto;
import br.com.projetospessoais.consultoraprodutos.services.ProdutoService;
import br.com.projetospessoais.consultoraprodutos.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
@Model
public class ProdutoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Produto produto;
    
    @Inject
    private ProdutoService produtoService;
    
    @Inject
    private Produto produtoSelecionado;
    
    private transient List<Produto> produtos;
    
    public List<Produto> getProdutos() {
        produtos = produtoService.findAll();
        return produtos;
    }

    public ProdutoBean() {
    }
    
    public void salvar() throws ConsultoraException {
        this.produtoService.save(produto);
        if (getEditando()) {
            FacesUtil.mensagemSucesso("Cadastro de produto '" + produto.getProduto() + "' atualizado com sucesso!");
            FacesUtil.redirecionaPara("PesquisaProduto.xhtml");
        } else {
            FacesUtil.mensagemSucesso("Cadastro efetuado com sucesso!");
        }
        produto = new Produto();
    }

    public void excluir() throws ConsultoraException {
        this.produtoService.delete(produtoSelecionado);
        FacesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
    }
    
    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro e retorna true, senao refere-se a um produto
     * a ser editado, retornando false.
     *
     * @return
     */
    public boolean getEditando() {
        return this.produto.getId() != null;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
}
