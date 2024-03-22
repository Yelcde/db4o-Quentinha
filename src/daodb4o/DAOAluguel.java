/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Pedido;

public class DAOAluguel  extends DAO<Pedido>{

	public Pedido read (Object chave){
		int id = (int) chave;	
		Query q = manager.query();
		q.constrain(Pedido.class);
		q.descend("id").constrain(id);
		List<Pedido> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//metodo sobrescrito da classe DAO para criar "id" sequencial 
	public void create(Pedido obj){
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store( obj );
	}

	//--------------------------------------------
	//  consultas de Aluguel
	//--------------------------------------------

	public List<Pedido> alugueisModelo(String modelo){
		Query q;
		q = manager.query();
		q.constrain(Pedido.class);
		q.descend("carro").descend("modelo").constrain(modelo);
		return q.execute();
	}

	public List<Pedido> alugueisFinalizados(){
		Query q = manager.query();
		q.constrain(Pedido.class);
		q.descend("finalizado").constrain(true);
		return q.execute();
	}
}
