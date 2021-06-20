package unoesc.edu.aulaJSP.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import unoesc.edu.aulaJSP.model.Produto;
import unoesc.edu.aulaJSP.model.Produto;

@FacesConverter(value = "produtoConverter", forClass = Produto.class)
public class ProdutoConverter implements javax.faces.convert.Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			Produto p = (Produto) component.getAttributes().get(value);
            return p;
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && (value instanceof Produto)) {
			Produto p = (Produto) value; 
			component.getAttributes().put( String.valueOf(p.getId()), p);
            return String.valueOf(p.getId());
        }

		return null;
	}

}
