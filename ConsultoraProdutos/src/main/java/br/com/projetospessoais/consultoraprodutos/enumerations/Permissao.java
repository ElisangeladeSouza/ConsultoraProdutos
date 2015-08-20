package br.com.projetospessoais.consultoraprodutos.enumerations;

import java.io.Serializable;

/**
 * Enumeraçao que retorna as opções de Roles(Funções) de cada
 * usuário do sistema. 
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public enum Permissao implements Serializable {

    ADMIN("Administrador");

    private final String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Permite que a descrição(valor) do item do ENUM seja retornado ao invés da
     * constante(chave).
     *
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }
        
	
}
