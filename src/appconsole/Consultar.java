/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Quentinha;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("consultas... \n");
			System.out.println("\npedidos na data 02/08/2024");
			for(Pedido p : Fachada.pedidosNaData("02/08/2024"))
				System.out.println(p);


			System.out.println("\nquentinhas do cliente Lucas");
			for(Quentinha q : Fachada.quentinhasDoCliente("Lucas"))
				System.out.println(q);


			System.out.println("\nclientes com mais de 2 pedidos");
			for(Cliente c : Fachada.clientesMaisDeNPedidos(2))
				System.out.println(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

