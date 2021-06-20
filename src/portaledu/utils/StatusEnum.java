package portaledu.utils;

public enum StatusEnum {
	
	ACTIVE("Ativo"), 
	INACTIVE("Inativo"), 
	BLOCKED("Bloqueado");
	
	private String label;

	StatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}