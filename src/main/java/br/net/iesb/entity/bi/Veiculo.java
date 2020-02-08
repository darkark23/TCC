package br.net.iesb.entity.bi;

public abstract class Veiculo {

	//makes it visible to all classes
	public String numeroFabricação;

	//makes it visible for the class only
	private String proprietario;

	//makes it visible for subclass
	protected String placa;

	//makes it visible to all classes of the same package
	String iptu;

	public void dirigir (){
		System.out.println("Dirigindo....");
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}

