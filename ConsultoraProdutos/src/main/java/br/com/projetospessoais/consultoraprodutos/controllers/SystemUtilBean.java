package br.com.projetospessoais.consultoraprodutos.controllers;

import java.io.Serializable;
import javax.enterprise.inject.Model;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * Classe utilitária que provê recursos que podem ser usados por todas as
 * páginas do sistema.
 *
 * @author lisa <elysangeladesouza@gmail.com>
 */
@Model
public class SystemUtilBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;

    /**
     * Este método é um pequeno utilitário para gerar um hash do tipo SHA-256 de
     * um texto qualquer, porém é mais usado em senhas.
     *
     * @param string
     */
    public void getHashText(String string) {
        Sha256Hash sha256Hash = new Sha256Hash(this.text);
        this.text = sha256Hash.toHex();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
