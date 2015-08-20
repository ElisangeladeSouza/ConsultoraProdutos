package br.com.projetospessoais.consultoraprodutos.util.shiro;

import javax.servlet.ServletRequest;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
       String message = ae.getMessage();
       request.setAttribute(getFailureKeyAttribute(), message);
    }
    
}
