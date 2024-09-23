package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pedido;

public class DAOPedido extends DAO<Pedido> {

	public Pedido read(Object chave) {
		try {
			int id = (int) chave; // casting para o tipo da chave
			TypedQuery<Pedido> query = manager.createQuery("select p from Pedido p where p.id = :x", Pedido.class);
			query.setParameter("x", id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// --------------------------------------------
	// consultas
	// --------------------------------------------

	public List<Pedido> readPedidosNaData(String data) {
		try {
			TypedQuery<Pedido> query = manager.createQuery("select p from Pedido p where p.data = :d", Pedido.class);
			query.setParameter("d", data);

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
