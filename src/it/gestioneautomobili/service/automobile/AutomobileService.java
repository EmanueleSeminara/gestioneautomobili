package it.gestioneautomobili.service.automobile;

import java.util.List;

import it.gestioneautomobili.dao.automobile.AutomobileDAO;
import it.gestioneautomobili.model.Automobile;

public interface AutomobileService {
	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile caricaSingolaAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile abitanteInstance) throws Exception;

	public void inserisciNuovo(Automobile abitanteInstance) throws Exception;

	public void rimuovi(Automobile abitanteInstance) throws Exception;

	// per injection
	public void setAutomobileDAO(AutomobileDAO abitanteDAO);
}
