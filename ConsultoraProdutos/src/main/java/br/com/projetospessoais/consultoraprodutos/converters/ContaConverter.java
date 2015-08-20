package br.com.projetospessoais.consultoraprodutos.converters;

import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Conta;
import br.com.projetospessoais.consultoraprodutos.services.ContaService;
import br.com.projetospessoais.consultoraprodutos.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author elisangela
 */
@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter {

    private final ContaService loginService;

    public ContaConverter() throws ConsultoraException {
        this.loginService = CDIServiceLocator.getBean(ContaService.class);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Conta objectToReturn = null;

        if (value != null) {
            objectToReturn = this.loginService.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long code = ((Conta) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
    
}
