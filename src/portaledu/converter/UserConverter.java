package portaledu.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import portaledu.model.UserModel;

@FacesConverter(value = "userConverter", forClass = UserModel.class)
public class UserConverter implements javax.faces.convert.Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			UserModel p = (UserModel) component.getAttributes().get(value);
            return p;
        }
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && (value instanceof UserModel)) {
			UserModel p = (UserModel) value; 
			component.getAttributes().put( String.valueOf(p.getId()), p);
            return String.valueOf(p.getId());
        }
		return null;
	}

}
