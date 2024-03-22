/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Usuario;

public class DAOUsuario extends DAO<Usuario>{

	public Usuario read (Object chave){
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("nome").constrain(nome);
		List<Usuario> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//metodo sobrescrito da classe DAO para criar "id" sequencial 
	public void create(Usuario obj){
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store( obj );
	}
	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
}

