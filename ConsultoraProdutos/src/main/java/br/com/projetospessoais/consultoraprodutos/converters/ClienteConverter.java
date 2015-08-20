package br.com.projetospessoais.consultoraprodutos.converters;

import br.com.projetospessoais.consultoraprodutos.util.cdi.CDIServiceLocator;
import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Cliente;
import br.com.projetospessoais.consultoraprodutos.services.ClienteService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {
    
    private final ClienteService clienteService;

    public ClienteConverter() throws ConsultoraException {
        this.clienteService = CDIServiceLocator.getBean(ClienteService.class);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente objectToReturn = null;

        if (value != null) {
            objectToReturn = this.clienteService.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long code = ((Cliente) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
    
}
