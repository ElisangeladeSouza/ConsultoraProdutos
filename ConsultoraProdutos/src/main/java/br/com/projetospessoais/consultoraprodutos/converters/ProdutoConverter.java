package br.com.projetospessoais.consultoraprodutos.converters;

import br.com.projetospessoais.consultoraprodutos.exceptions.ConsultoraException;
import br.com.projetospessoais.consultoraprodutos.model.Produto;
import br.com.projetospessoais.consultoraprodutos.services.ProdutoService;
import br.com.projetospessoais.consultoraprodutos.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author elisangela
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {
    
    private final ProdutoService produtoService;

    public ProdutoConverter() throws ConsultoraException {
        this.produtoService = CDIServiceLocator.getBean(ProdutoService.class);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Produto objectToReturn = null;

        if (value != null) {
            objectToReturn = this.produtoService.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long code = ((Produto) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
    
}
