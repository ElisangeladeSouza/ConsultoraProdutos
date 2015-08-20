package br.com.projetospessoais.consultoraprodutos.util.jsf;

import br.com.projetospessoais.consultoraprodutos.exceptions.NegocioException;
import java.io.IOException;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Empacota o tratador de exceções do JSF e funciona como uma camada acima do
 * mesmo para tratar as exceções capturadas
 *
 * @see
 * http://docs.oracle.com/javaee/6/api/javax/faces/context/ExceptionHandler.html
 *
 * @author cassio
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private static final Log LOGGER = LogFactory.getLog(JsfExceptionHandler.class);

//    Recebe o tratador de exceções do JSF através da atribuição do construtor da classe
    private final ExceptionHandler empacotado;

    /**
     *
     * @param wrapped
     */
    public JsfExceptionHandler(ExceptionHandler wrapped) {
        this.empacotado = wrapped;
    }

    /**
     *
     * @return
     */
    @Override
    public ExceptionHandler getWrapped() {
        return this.empacotado;
    }

    /**
     * Método que é chamado quando houver uma exceção. O JSF instancia a classe
     * passando como parametro o ExceptionHandler e chamando esse método que é
     * responsável por tratar as exceções lançadas que o JSF consegue capturar.
     *
     * @throws FacesException
     */
    @Override
    public void handle() {
        //Todos os eventos de exceções são  enfileirados nesse iterador.
        Iterator<ExceptionQueuedEvent> eventos = getUnhandledExceptionQueuedEvents().iterator();
        /*
         Enquanto houverem eventos no iterador, pega o próximo evento. 
         Pega o evento da origem da exceção no contexto do mesmo, através do getSource.
         */
        while (eventos.hasNext()) {
            ExceptionQueuedEvent evento = eventos.next();
            ExceptionQueuedEventContext contexto = (ExceptionQueuedEventContext) evento.getSource();

            //retorna a exceção lançada
            Throwable excecao = contexto.getException();
            NegocioException negocioException = getNegocioException(excecao);

            //Informa quando a mensagem é tratada para que ela não seja removida no finally
            boolean handled = false;

            try {
                /*Tratando as exceções capturadas*/
                //Condição tratando o ViewExpiredException
                if (excecao instanceof ViewExpiredException) {
                    redirecionar("/");//Redirecionando para a home
                } else if (negocioException != null) {
                    handled = true;
                    FacesUtil.mensagemErro(negocioException.getMessage());
                } else {
                    handled = true;
                    LOGGER.error("ERRO NO SISTEMA: " + excecao.getMessage(), excecao);
                    redirecionar("/erro.xhtml");
                }
            } finally {
                if (handled) {
                    eventos.remove();
                }
            }
        }
        //Diz que a exceção que capturamos foi tratada e que o tratador pode 
        //receber e tratar as exceções de acordo com a regra do framework
        getWrapped().handle();
    }

    /**
     * Captura o contexto da aplicação (nome) e a retorna quando o método é
     * chamado.
     *
     * @param string
     */
    private void redirecionar(String pagina) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String contextPath = externalContext.getRequestContextPath();

            externalContext.redirect(contextPath + pagina);

            //Informa que a resposta está completa e evita quaisquer processamento indevido
            facesContext.responseComplete();
        } catch (IOException iOException) {
            throw new FacesException(iOException);
        }
    }

    private NegocioException getNegocioException(Throwable exception) {
        if (exception instanceof NegocioException) {
            return (NegocioException) exception;
        } else if (exception.getCause() != null) {
            return getNegocioException(exception.getCause());
        }
        return null;
    }

}
