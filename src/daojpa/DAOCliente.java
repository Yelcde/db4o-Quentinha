/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
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
        query.constrain(new FiltroPedidos(numPedidos));
		List<Cliente> clientes = query.execute();

		return clientes;
    }
}

class FiltroPedidos implements Evaluation {
    private int numPedidos;

    public FiltroPedidos(int numPedidos) {
        this.numPedidos = numPedidos;
    }

    @Override
    public void evaluate(Candidate candidate) {
        Cliente cliente = (Cliente) candidate.getObject();
        if (cliente.getPedidos().size() > this.numPedidos)
        	candidate.include(true);
        else
        	candidate.include(false);
    }
}

