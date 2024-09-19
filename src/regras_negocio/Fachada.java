/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package regras_negocio;

import java.util.List;

import daojpa.DAO;
import daojpa.DAOCliente;
import daojpa.DAOPedido;
import daojpa.DAOQuentinha;
import modelo.Pedido;
import modelo.Quentinha;
import modelo.Cliente;

public class Fachada {
	private static DAOQuentinha daoquentinha= new DAOQuentinha();  
	private static DAOPedido daopedido= new DAOPedido(); 
	private static DAOCliente daocliente = new DAOCliente();

	private Fachada() {}
	
	public static void inicializar() {
		DAO.open();
	}
	
	public static void finalizar() {
		DAO.close();
	}

	//------------------ CRUD Quentinha --------------------------
	
	public static Quentinha localizarQuentinha(String descricao) {
		return daoquentinha.read(descricao);
	}
	
	public static Quentinha cadastrarQuentinha(String descricao, double preco) throws Exception {
		DAO.begin();
		
		Quentinha quentinha = daoquentinha.read(descricao);
		
		if (quentinha != null) {
			DAO.rollback();
			throw new Exception("Quentinha ja cadastrado:" + descricao);
		}
		
		quentinha = new Quentinha(descricao, preco);

		daoquentinha.create(quentinha);
		DAO.commit();
		
		return quentinha;
	}
	
	public static List<Quentinha> listarQuentinhas() {
		List<Quentinha> quentinhas = daoquentinha.readAll();
		
		return quentinhas;
	}

	public static Quentinha atualizarDescricaoQuentinha(String descricao, String novaDescricao) throws Exception {
		DAO.begin();
		
		Quentinha quentinha = daoquentinha.read(descricao);
		
		if (quentinha == null) {
			DAO.rollback();
			throw new Exception("Quentinha incorreta para atualizacao:" + descricao);
		}
		
		quentinha.setDescricao(novaDescricao);
		daoquentinha.update(quentinha);
		DAO.commit();
		
		return quentinha;
	}
	
	public static Quentinha atualizarPrecoQuentinha(String descricao, double novoPreco) throws Exception {
		DAO.begin();
		
		Quentinha quentinha = daoquentinha.read(descricao);
		
		if (quentinha == null) {
			DAO.rollback();
			throw new Exception("Quentinha incorreta para atualizacao:" + descricao);
		}
		
		quentinha.setPreco(novoPreco);
		daoquentinha.update(quentinha);
		DAO.commit();
		
		return quentinha;
	}
	
	public static void excluirQuentinha(String descricao) throws Exception {
		DAO.begin();
		Quentinha quentinha = daoquentinha.read(descricao);
		
		if(quentinha == null) {
			DAO.rollback();
			throw new Exception ("Quentinha incorreta para exclusao " + descricao);
		}

		for (Pedido pedido : quentinha.getPedidos()) {
			Cliente cliente = pedido.getCliente();
			cliente.remover(pedido);
			
			daocliente.update(cliente);
			daopedido.delete(pedido);
		}
		
		daoquentinha.delete(quentinha);
		DAO.commit();
	}

	//------------------ CRUD Cliente --------------------------
	
	public static Cliente cadastrarCliente(String nome, String telefone) throws Exception{
		DAO.begin();
		
		Cliente cliente = daocliente.read(nome);
		
		if (cliente != null) {
			DAO.rollback();
			throw new Exception("Pessoa ja cadastrado:" + nome);	
		}
			
		cliente = new Cliente(nome, telefone);

		daocliente.create(cliente);
		DAO.commit();
		
		return cliente;
	}
	
	public static Cliente localizarCliente(String nome) {
		return daocliente.read(nome);
	}
	
	public static List<Cliente> listarClientes() {
		List<Cliente> clientes =  daocliente.readAll();
		
		return clientes;
	}
	
	public static Cliente atualizarNomeCliente(String nome, String novoNome) throws Exception {
		DAO.begin();
		
		Cliente cliente = daocliente.read(nome);
		
		if (cliente == null) {
			DAO.rollback();
			throw new Exception("Pessoa incorreta para atualizacao:" + nome);
		}
		
		cliente.setNome(novoNome);
		daocliente.update(cliente);
		DAO.commit();
		
		return cliente;
	}
	
	public static Cliente atualizarTelefoneCliente(String nome, String novoTelefone) throws Exception {
		DAO.begin();
		
		Cliente cliente = daocliente.read(nome);
		
		if (cliente == null) {
			DAO.rollback();
			throw new Exception("Pessoa incorreta para atualizacao:" + nome);
		}
		
		cliente.setTelefone(novoTelefone);
		daocliente.update(cliente);
		DAO.commit();
		
		return cliente;
	}
	
	public static void excluirCliente(String nome) throws Exception {
		DAO.begin();
		
		Cliente cliente =  daocliente.read(nome);
		
		if(cliente == null) {
			DAO.rollback();
			throw new Exception ("cliente incorreto para exclusao " + nome);
		}
		 
		for (Pedido pedido : cliente.getPedidos()) {
			Quentinha quentinha = pedido.getQuentinha();
			
			quentinha.remover(pedido);
			cliente.remover(pedido);
			
			daopedido.delete(pedido);
		}

		daocliente.delete(cliente);
		DAO.commit();
	}
	
	//------------------ CRUD Pedido --------------------------
	
	public static Pedido cadastrarPedido(String nomeCliente, String quentinhaDescricao, String tamanho, String data) throws Exception {
		DAO.begin();

		Cliente cliente = daocliente.read(nomeCliente);
		Quentinha quentinha = daoquentinha.read(quentinhaDescricao);
		
		if (cliente == null) {
			DAO.rollback();
			throw new Exception("Pessoa nao cadastrado:" + nomeCliente);	
		}
		
		if (quentinha == null) {
			DAO.rollback();
			throw new Exception("Quentinha nao cadastrada:" + quentinhaDescricao);
		}
		
		double valorPago = quentinha.getPreco();
		String perfil = cliente.getPerfil();
		
		
		
		if (perfil.equalsIgnoreCase("ouro")) {
			valorPago = quentinha.getPreco() - (quentinha.getPreco() * 0.25);
		} else if (perfil.equalsIgnoreCase("prata")) {
			valorPago = quentinha.getPreco() - (quentinha.getPreco() * 0.15);
		} else if (perfil.equalsIgnoreCase("bronze")) {
			valorPago = quentinha.getPreco() - (quentinha.getPreco() * 0.05);
		}
		
		Pedido pedido = new Pedido(cliente, quentinha, tamanho, data);
		pedido.setValorPago(valorPago);
		daopedido.create(pedido);
		
		cliente.adicionar(pedido);
		daocliente.update(cliente);
		
		quentinha.adicionar(pedido);		
		daoquentinha.update(quentinha);
		
		DAO.commit();
		
		return pedido;
	}

	public static List<Pedido> listarPedidos() {
		List<Pedido> pedidos =  daopedido.readAll();
		
		return pedidos;
	}

	public static Pedido atualizarTamanhoPedido(int id, String novoTamanho) throws Exception {
		DAO.begin();
		
		Pedido pedido = daopedido.read(id);
		
		if (pedido == null) {
			DAO.rollback();
			throw new Exception("Pedido incorreto para atualizacao:" + id);
		}
		
		pedido.setTamanho(novoTamanho);
		daopedido.update(pedido);
		DAO.commit();
		
		return pedido;
	}
	
	public static Pedido atualizarDataPedido(int id, String novaData) throws Exception {
		DAO.begin();
		
		Pedido pedido = daopedido.read(id);
		
		if (pedido == null) {
			DAO.rollback();
			throw new Exception("Pedido incorreto para atualizacao:" + id);
		}
		
		pedido.setData(novaData);
		daopedido.update(pedido);
		DAO.commit();
		
		return pedido;
	}
	
	public static void excluirPedido(int id) throws Exception {
		DAO.begin();
		Pedido pedido = daopedido.read(id);
		
		if(pedido == null) {
			DAO.rollback();
			throw new Exception ("Pedido incorreta para exclusao " + id);
		}

		Cliente cliente = pedido.getCliente();
		Quentinha quentinha = pedido.getQuentinha();
		
		cliente.remover(pedido);
		daocliente.update(cliente);
		
		quentinha.remover(pedido);
		daoquentinha.update(quentinha);
		
		daopedido.delete(pedido);
		DAO.commit();
	}
	
	//------------------ Consultas ------------------------------------
	
	public static List<Pedido> pedidosNaData(String data) {
		List<Pedido> pedidos =  daopedido.readPedidosNaData(data);
		
		return pedidos;
	}
	
	public static List<Quentinha> quentinhasDoCliente(String nome) {
		List<Quentinha> quentinhas =  daoquentinha.readQuentinhasDeCliente(nome);
		
		return quentinhas;
	}
	
	public static List<Cliente> clientesMaisDeNPedidos(int n) {
		List<Cliente> clientes = daocliente.readNPedidos(n);
		
		return clientes;
	}

//	
}
