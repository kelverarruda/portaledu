package portaledu.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import portaledu.model.StudentModel;

@FacesConverter(value = "studentConverter", forClass = StudentModel.class)
public class StudentConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			StudentModel p = (StudentModel) component.getAttributes().get(value);
            return p;
        }
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && (value instanceof StudentModel)) {
			StudentModel p = (StudentModel) value; 
			component.getAttributes().put( String.valueOf(p.getId()), p);
            return String.valueOf(p.getId());
        }
		
		return null;
	}

}
