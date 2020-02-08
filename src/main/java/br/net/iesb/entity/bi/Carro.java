package br.net.iesb.entity.bi;

public class Carro extends Veiculo{

	public Integer placa;
	public String iptu;
	public String proprietario;
	public Integer numeroFabricação;

	@Override
	public void dirigir() {
		super.dirigir();
		System.out.println(this.placa);
		System.out.println(this.numeroFabricação);
	}
}

