package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Quentinha;

public class DAOQuentinha extends DAO<Quentinha> {
	public Quentinha read(Object chave) {
		try {
			String descricao = (String) chave; // casting para o tipo da chave
			TypedQuery<Quentinha> query = manager.createQuery("select q from Quentinha q where q.descricao = :n",
					Quentinha.class);
			query.setParameter("n", descricao);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// --------------------------------------------
	// consultas
	// --------------------------------------------

	public List<Quentinha> readQuentinhasDeCliente(String nome) {
		try {
			TypedQuery<Quentinha> query = manager.createQuery(
					"SELECT q FROM Quentinha q JOIN q.pedidos p WHERE p.cliente.nome = :n", Quentinha.class);
			query.setParameter("n", nome);

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
