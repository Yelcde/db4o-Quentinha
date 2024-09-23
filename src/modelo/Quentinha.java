package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Quentinha {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quentinha_seq")
	@SequenceGenerator(name = "quentinha_seq", sequenceName = "quentinha_seq", allocationSize = 1)
	private int id;

	private String descricao;

	private double preco;

	@OneToMany(mappedBy = "quentinha", cascade = CascadeType.REFRESH)
	List<Pedido> pedidos = new ArrayList<>();

	public Quentinha(String descricao, double preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

	public Quentinha() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void adicionar(Pedido a) {
		pedidos.add(a);
	}

	public void remover(Pedido a) {
		pedidos.remove(a);
	}

	@Override
	public String toString() {
		return "Quentinha [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", pedidos=" + pedidos + "]";
	}

}
