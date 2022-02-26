package it.gestioneautomobili.dao;

import it.gestioneautomobili.dao.automobile.AutomobileDAO;
import it.gestioneautomobili.dao.automobile.AutomobileDAOImpl;
import it.gestioneautomobili.dao.proprietario.ProprietarioDAO;
import it.gestioneautomobili.dao.proprietario.ProprietarioDAOImpl;

public class MyDaoFactory {
	private static AutomobileDAO automobileDAOInstance = null;
	private static ProprietarioDAO proprietarioDAOInstance = null;

	public static AutomobileDAO getAutomobileDAOInstance() {
		if (automobileDAOInstance == null)
			automobileDAOInstance = new AutomobileDAOImpl();
		return automobileDAOInstance;
	}

	public static ProprietarioDAO getProprietarioDAOInstance() {
		if (proprietarioDAOInstance == null)
			proprietarioDAOInstance = new ProprietarioDAOImpl();
		return proprietarioDAOInstance;
	}
}
