/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import modelo.Pedido;
import modelo.Quentinha;
import modelo.Cliente;
import modelo.Usuario;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de carros:");
			for(Quentinha c: Fachada.listarCarros())
				System.out.println(c);

			System.out.println("\n---listagem de clientes:");
			for(Cliente c: Fachada.listarClientes())
				System.out.println(c);
			
			System.out.println("\n---listagem de alugueis:");
			for(Pedido c: Fachada.listarAlugueis())
				System.out.println(c);

			System.out.println("\n---listagem de usuarios:");
			for(Usuario u: Fachada.listarUsuarios())
				System.out.println(u);
			
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
