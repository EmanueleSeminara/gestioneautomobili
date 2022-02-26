package it.gestioneautomobili.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.gestioneautomobili.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO {
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Proprietario> list() throws Exception {
		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();

	}

	@Override
	public Proprietario get(Long id) throws Exception {
		return entityManager.find(Proprietario.class, id);

	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		proprietarioInstance = entityManager.merge(proprietarioInstance);
	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietarioInstance);
	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(proprietarioInstance));
	}

	@Override
	public int countByImmatricolateDopoAnno(int annoImmatricolazioneInput) throws Exception {
		TypedQuery<Proprietario> query = entityManager.createQuery(
				"select distinct p from Proprietario p join p.automobili au where au.annoImmatricolazione > ?1",
				Proprietario.class);

		query.setParameter(1, annoImmatricolazioneInput);

		// return query.getSingleResult() ha il problema che se non trova elementi
		// lancia NoResultException
		return query.getResultList().size();
	}

}
