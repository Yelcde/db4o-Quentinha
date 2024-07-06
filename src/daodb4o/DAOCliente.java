/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Cliente;

public class DAOCliente extends DAO<Cliente> {
    public Cliente read(Object chave) {
        String nome = (String) chave;    // casting para o tipo da chave

        Query q = manager.query();
        q.constrain(Cliente.class);
        q.descend("nome").constrain(nome);
        List<Cliente> resultados = q.execute();

        if (resultados.size() > 0) return resultados.get(0);

        return null;
    }

    //--------------------------------------------
    //  consultas
    //--------------------------------------------

    public List<Cliente> readNPedidos(int numPedidos) {
        Query query = manager.query();
        query.constrain(Cliente.class);
        query.descend("pedidos").descend("size").constrain(numPedidos).greater();
		List<Cliente> clientes = query.execute();

		return clientes;
    }
}

