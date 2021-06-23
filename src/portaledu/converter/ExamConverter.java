package portaledu.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import portaledu.model.ExamModel;

@FacesConverter(value = "examConverter", forClass = ExamModel.class)
public class ExamConverter implements javax.faces.convert.Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			ExamModel p = (ExamModel) component.getAttributes().get(value);
            return p;
        }
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && (value instanceof ExamModel)) {
			ExamModel p = (ExamModel) value; 
			component.getAttributes().put( String.valueOf(p.getId()), p);
            return String.valueOf(p.getId());
        }
		return null;
	}

}