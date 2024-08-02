/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {
    public Cadastrar() {
    	Fachada.inicializar();
    	
        try {            
            System.out.println("cadastrando cliente...");
            Fachada.cadastrarCliente("Lucas", "83988886666");
            Fachada.cadastrarCliente("Johnner", "83988886677");
            Fachada.cadastrarCliente("Jessye", "83988886688");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
        	System.out.println("cadastrando quentinha...");
            Fachada.cadastrarQuentinha("Macarrão com frango", 14.00);
            Fachada.cadastrarQuentinha("Lasanha", 16.00);
            Fachada.cadastrarQuentinha("Arroz, feijao e carne", 18.00);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        try {
        	System.out.println("cadastrando pedido...");
            Fachada.cadastrarPedido("Lucas", "Macarrão com frango", "P", "02/08/2024");
            Fachada.cadastrarPedido("Lucas", "Macarrão com frango", "G", "02/08/2024");
            Fachada.cadastrarPedido("Lucas", "Macarrão com frango", "G", "01/08/2024");
            Fachada.cadastrarPedido("Jessye", "Arroz, feijao e carne", "P", "07/07/2024");
            Fachada.cadastrarPedido("Jessye", "Macarrão com frango", "G", "02/08/2024");
            Fachada.cadastrarPedido("Johnner", "Lasanha", "G", "18/07/2024");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        Fachada.finalizar();
        System.out.println("\nfim do programa !");
    }
    
    public static void main(String[] args) {
        new Cadastrar();
    }
}
