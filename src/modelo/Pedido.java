package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Quentinha quentinha;
	
	private String tamanho;
	
	private String data;

	private double valorPago;

	public Pedido() {
	}

	public Pedido(Cliente cliente, Quentinha quentinha, String tamanho, String data) {
		super();
		this.cliente = cliente;
		this.quentinha = quentinha;
		this.tamanho = tamanho;
		this.data = data;
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

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente.getNome() + ", quentinha=" + quentinha.getDescricao()
				+ ", tamanho=" + tamanho + ", data=" + data + ", valorPago=" + valorPago + "]";
	}
}
