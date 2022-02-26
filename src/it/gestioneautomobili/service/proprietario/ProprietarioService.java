package it.gestioneautomobili.service.proprietario;

import java.util.List;

import it.gestioneautomobili.dao.proprietario.ProprietarioDAO;
import it.gestioneautomobili.model.Proprietario;

public interface ProprietarioService {
	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	// per injection
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
}
