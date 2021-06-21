package portaledu.utils;

public enum UserTypeEnum {

	ADMIN("Administrador"),
	PROF("Professor"), 
	STUDENT("Aluno");
	
	private String label;

	UserTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
	
}
