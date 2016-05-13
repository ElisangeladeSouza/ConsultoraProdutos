package br.com.projetospessoais.consultoraprodutos.converters;

import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Usuario;
import br.com.projetospessoais.consultoraprodutos.services.UsuarioService;
import br.com.projetospessoais.consultoraprodutos.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author elisangela
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    private final UsuarioService usuarioService;

    public UsuarioConverter() throws ConsultoraException {
        this.usuarioService = CDIServiceLocator.getBean(UsuarioService.class);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario objectToReturn = null;

        if (value != null) {
            objectToReturn = this.usuarioService.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long code = ((Usuario) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
    
}
