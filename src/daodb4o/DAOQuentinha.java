/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Quentinha;

public class DAOQuentinha extends DAO<Quentinha> {
	public Quentinha read (Object chave){
		String descricao = (String) chave;	//casting para o tipo da chave
		
		Query q = manager.query();
		q.constrain(Quentinha.class);
		q.descend("placa").constrain(descricao);
		List<Quentinha> resultados = q.execute();
		
		if (resultados.size()>0) return resultados.get(0);
		
		return null;
	}
	
    //--------------------------------------------
    //  consultas
    //--------------------------------------------
	
	public List<Quentinha> readQuentinhasDeCliente(String nome) {
		Query query = manager.query();
		query.constrain(Quentinha.class);
		query.descend("pedidos").descend("cliente").descend("nome").constrain(nome);
		List<Quentinha> quentinhas = query.execute();
		
		return quentinhas;
	}
}
