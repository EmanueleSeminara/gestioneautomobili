package it.gestioneautomobili.dao.automobile;

import java.util.List;

import it.gestioneautomobili.dao.IBaseDAO;
import it.gestioneautomobili.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile> {
	public List<Automobile> findAllByCodiceFiscaleIniziaCon(String inizialeCodiceFiscale) throws Exception;
}
