package br.com.projetospessoais.consultoraprodutos.util.jsf;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe com métodos utilitários para adicionar mensagens às telas, geralmente,
 * através dos ManagedBeans.
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class FacesUtil {

    private static final Log LOGGER = LogFactory.getLog(FacesUtil.class);

    private FacesUtil() {
    }

    /**
     * Método que adiciona uma mensagem com nível de severidade de
     * <b>INFORMAÇÃO/SUCESSO</b>
     *
     * @param mensagem
     */
    public static void mensagemSucesso(String mensagem) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    /**
     * Método que adiciona uma mensagem com nível de severidade de <b>AVISO</b>
     *
     * @param mensagem
     */
    public static void mensagemAviso(String mensagem) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage("warningInfo", facesMsg);
    }

    /**
     * Método que retorna uma mensagem com nível de severidade de <b>ERRO</b>
     *
     * @param mensagem
     */
    public static void mensagemErro(String mensagem) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * Método que retorna uma mensagem com nível de severidade de <b>ERRO</b> e
     * também a causa da exceção
     *
     * @param ex
     * @param mensagemPadrao
     */
    public static void mensagemErro(Exception ex, String mensagemPadrao) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            mensagemErro(msg);
        } else {
            mensagemErro(mensagemPadrao);
        }
    }

    /**
     * Método que recebe uma pagina com extensão (.xhtml, .jsf, .jsp, etc) como
     * parâmetro e, usando o contexto do JSF, faz o redirecionamento para a
     * página informada. Por padrão, uma mensagem passada pelo FacesMessage, não
     * é mantida quando um 'redirect' é feito, a menos que o escopo do Bean seja
     * de sessão (o que não é muito legal a menos que seja em casos
     * especificos). Para resolver isso, é usado o, não tão conhecido, escopo
     * 'Flash' que mantém a mensagem mesmo após o redirecionamto de página.
     *
     * @param pagina
     */
    public static void redirecionaPara(String pagina) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (IOException ex) {
            LOGGER.error(FacesUtil.class.getName() + ex);
        }
    }
}
