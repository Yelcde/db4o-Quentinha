package modelo;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String telefone;
	private String perfil = "Nenhum";
	private ArrayList <Pedido> pedidos = new ArrayList<>();

	public Cliente(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public Cliente() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void adicionar(Pedido a) {
		if (pedidos.size() == 2) {
			perfil = "Bronze";
		} else if (pedidos.size() == 4) {
			perfil = "Prata";
		} else if (pedidos.size() > 4) {
			perfil = "Ouro";
		}
		
		pedidos.add(a);	
	}

	public void remover(Pedido a) {
		pedidos.remove(a);
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", perfil=" + perfil + ", pedidos=" + pedidos + "]";
	}

}
