package portaledu.utils;

public enum StatusesEnum {
	
	ACTIVE("Ativo"), 
	INACTIVE("Inativo"), 
	BLOCKED("Bloqueado");
	
	private String label;

	StatusesEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}