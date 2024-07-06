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
        try {
            Fachada.inicializar();
            System.out.println("cadastrando cliente...");
            Fachada.cadastrarCliente("Lucas", "83988886666");
            Fachada.cadastrarCliente("Johnner", "83988886677");
            Fachada.cadastrarCliente("Jessye", "83988886688");
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
