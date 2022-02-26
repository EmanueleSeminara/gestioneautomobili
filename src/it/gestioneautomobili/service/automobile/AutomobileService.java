package it.gestioneautomobili.service.automobile;

import java.util.List;

import it.gestioneautomobili.dao.automobile.AutomobileDAO;
import it.gestioneautomobili.model.Automobile;

public interface AutomobileService {
	public List<Automobile> listAllAbitanti() throws Exception;

	public Automobile caricaSingoloAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;

	// per injection
	public void setAutomobileDAO(AutomobileDAO automobileDAO);
}
