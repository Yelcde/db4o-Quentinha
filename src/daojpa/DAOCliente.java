package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Cliente;

public class DAOCliente extends DAO<Cliente> {
	public Cliente read(Object chave) {
		try {
			String nome = (String) chave; // casting para o tipo da chave
			TypedQuery<Cliente> query = manager.createQuery("select c from Cliente c where c.nome = :n", Cliente.class);
			query.setParameter("n", nome);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// --------------------------------------------
	// consultas
	// --------------------------------------------

	public List<Cliente> readNPedidos(int numPedidos) {
		try {
			TypedQuery<Cliente> query = manager.createQuery("select c from Cliente c where SIZE(c.pedidos) = :n",
					Cliente.class);
			query.setParameter("n", numPedidos);

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
