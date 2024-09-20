package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Quentinha {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String descricao;

	private double preco;

	@OneToMany(mappedBy = "quentinha", cascade = CascadeType.ALL)
	ArrayList<Pedido> pedidos = new ArrayList<>();

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

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
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
