package appconsole;

import modelo.Pedido;
import modelo.Quentinha;
import modelo.Cliente;
import regras_negocio.Fachada;

public class Listar {
	public Listar() {
		try {
			Fachada.inicializar();

			System.out.println("\n---listagem de clientes:");
			for(Cliente c: Fachada.listarClientes()) {
				System.out.println(c);
			}
			
			System.out.println("\n---listagem de quentinhas:");
			for(Quentinha q: Fachada.listarQuentinhas()) {
				System.out.println(q);
			}
			
			System.out.println("\n---listagem de pedidos:");
			for(Pedido p: Fachada.listarPedidos()) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
