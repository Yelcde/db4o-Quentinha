/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;
import com.db4o.query.Query;

import modelo.Pedido;
import modelo.Quentinha;

public class DAOQuentinha extends DAO<Quentinha> {
	public Quentinha read (Object chave){
		String descricao = (String) chave;	//casting para o tipo da chave
		
		Query q = manager.query();
		q.constrain(Quentinha.class);
		q.descend("descricao").constrain(descricao);
		List<Quentinha> resultados = q.execute();
		
		if (resultados.size()>0) return resultados.get(0);
		
		return null;
	}
	
	// metodo sobrescrito da classe DAO para criar "id" sequencial 
	public void create(Quentinha obj) {
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store(obj);
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
