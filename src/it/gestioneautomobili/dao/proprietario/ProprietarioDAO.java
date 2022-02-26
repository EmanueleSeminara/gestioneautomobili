package it.gestioneautomobili.dao.proprietario;

import it.gestioneautomobili.dao.IBaseDAO;
import it.gestioneautomobili.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario> {
	public int countByImmatricolateDopoAnno(int annoImmatricolazioneInput) throws Exception;
}
