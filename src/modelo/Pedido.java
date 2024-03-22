package modelo;

public class Pedido {
	private int id;			//autogerado no daoaluguel
	private Cliente cliente;
	private Quentinha quentinha;
	private String tamanho;
	private String data;
	private double valorPago;

	public Pedido() {}

	public Pedido(Cliente cliente, Quentinha quentinha, String tamanho, String data) {
		super();
		this.cliente = cliente;
		this.quentinha = quentinha;
		this.tamanho = tamanho;
		this.data = data;
		
//		LocalDate data = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String perfil = cliente.getPerfil();
		
		if (perfil.equalsIgnoreCase("ouro")) {
			this.valorPago = quentinha.getPreco() - (quentinha.getPreco() * 0.25);
		} else if (perfil.equalsIgnoreCase("prata")) {
			this.valorPago = quentinha.getPreco() - (quentinha.getPreco() * 0.15);
		} else if (perfil.equalsIgnoreCase("bronze")) {
			this.valorPago = quentinha.getPreco() - (quentinha.getPreco() * 0.05);
		} else {
			this.valorPago = quentinha.getPreco();
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Quentinha getQuentinha() {
		return quentinha;
	}

	public void setQuentinha(Quentinha quentinha) {
		this.quentinha = quentinha;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	

}
