/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package regras_negocio;

import java.util.List;
import daodb4o.DAO;
import daodb4o.DAOPedido;
import daodb4o.DAOQuentinha;
import daodb4o.DAOCliente;
import modelo.Pedido;
import modelo.Quentinha;
import modelo.Cliente;

public class Fachada {
	private Fachada() {}

	private static DAOQuentinha daoquentinha= new DAOQuentinha();  
	private static DAOPedido daopedido= new DAOPedido(); 
	private static DAOCliente daocliente = new DAOCliente(); 
//	private static DAOUsuario daousuario = new DAOUsuario(); 
//	public static Usuario logado;	//contem o objeto Usuario logado em TelaLogin.java

	public static void inicializar() {
		DAO.open();
	}
	
	public static void finalizar() {
		DAO.close();
	}

	public static Quentinha cadastrarQuentinha(String descricao, double preco) throws Exception {
		DAO.begin();
		
		Quentinha quentinha = daoquentinha.read(descricao);
		
		if (quentinha!=null) throw new Exception("Quentinha ja cadastrado:" + descricao);
		
		quentinha = new Quentinha(descricao, preco);

		daoquentinha.create(quentinha);
		DAO.commit();
		
		return quentinha;
	}
	
	public static List<Quentinha> listarQuentinhas() {
		List<Quentinha> quentinhas = daoquentinha.readAll();
		
		return quentinhas;
	}

	public static void excluirQuentinha(String descricao) throws Exception {
		DAO.begin();
		Quentinha quentinha = daoquentinha.read(descricao);
		
		if(quentinha == null) throw new Exception ("Quentinha incorreta para exclusao " + descricao);

		for (Pedido pedido : quentinha.getPedidos()) {
			Cliente cliente = pedido.getCliente();
			cliente.remover(pedido);
			
			daocliente.update(cliente);
			daopedido.delete(pedido);
		}
		
		daoquentinha.delete(quentinha);
		DAO.commit();
	}

	public static Cliente cadastrarCliente(String nome, String cpf) throws Exception{
		DAO.begin();
		Cliente cli = daocliente.read(cpf);
		if (cli!=null)
			throw new Exception("Pessoa ja cadastrado:" + cpf);
		cli = new Cliente(nome, cpf);

		daocliente.create(cli);
		DAO.commit();
		return cli;
	}
	
	public static List<Cliente> listarClientes() {
		List<Cliente> clientes =  daocliente.readAll();
		
		return clientes;
	}
	
	public static void excluirCliente(String nome) throws Exception {
		DAO.begin();
		
		Cliente cliente =  daocliente.read(nome);
		
		if(cliente == null) throw new Exception ("cliente incorreto para exclusao " + nome);
		 
		for (Pedido pedido : cliente.getPedidos()) {
			Quentinha quentinha = pedido.getQuentinha();
			
			quentinha.remover(pedido);
			cliente.remover(pedido);
			
			daopedido.delete(pedido);
		}

		daocliente.delete(cliente);
		DAO.commit();
	}
	
	//------------------Usuario------------------------------------

//	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
//		DAO.begin();
//		Usuario usu = daousuario.read(nome);
//		if (usu!=null)
//			throw new Exception("Usuario ja cadastrado:" + nome);
//		usu = new Usuario(nome, senha);
//
//		daousuario.create(usu);
//		DAO.commit();
//		return usu;
//	}
//	public static Usuario localizarUsuario(String nome, String senha) {
//		Usuario usu = daousuario.read(nome);
//		if (usu==null)
//			return null;
//		if (! usu.getSenha().equals(senha))
//			return null;
//		return usu;
//	}
}
