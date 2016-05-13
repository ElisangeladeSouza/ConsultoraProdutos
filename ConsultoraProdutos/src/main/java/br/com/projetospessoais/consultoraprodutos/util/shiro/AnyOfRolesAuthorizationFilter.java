package br.com.projetospessoais.consultoraprodutos.util.shiro;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

/**
 * Classe AnyOfRolesAuthorizationFilter faz uma implementação personalizada do
 * filtro de papéis, OR para o papel fornecido em vez de AND. Permitindo que uma
 * determinada url seja compartilhada por mais de um papel. Exemplo.:
 * /path/to/some/url = anyofroles["role1,role2"]
 *
 * @author lisa <elysangeladesouza@gmail.com>
 */
public class AnyOfRolesAuthorizationFilter extends RolesAuthorizationFilter {
    
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response,
            Object mappedValue) throws IOException {

        final Subject subject = getSubject(request, response);
        final String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }

        for (String roleName : rolesArray) {
            if (subject.hasRole(roleName)) {
                return true;
            }
        }
        return false;
    }
}
