/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Pedido;

public class DAOPedido extends DAO<Pedido> {
	public Pedido read (Object chave) {
		int id = (int) chave;
		
		Query q = manager.query();
		q.constrain(Pedido.class);
		q.descend("id").constrain(id);
		List<Pedido> resultados = q.execute();
		
		if (resultados.size()>0) return resultados.get(0);

		return null;
	}

	// metodo sobrescrito da classe DAO para criar "id" sequencial 
	public void create(Pedido obj) {
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store(obj);
	}
	
    //--------------------------------------------
    //  consultas
    //--------------------------------------------
	
	public List<Pedido> readPedidosNaData(String data) {
		Query query = manager.query();
		query.constrain(Pedido.class);
		query.descend("data").constrain(data);
		List<Pedido> pedidos = query.execute();
		
		return pedidos;
	}
}
