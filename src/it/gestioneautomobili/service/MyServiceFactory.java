package it.gestioneautomobili.service;

import it.gestioneautomobili.dao.MyDaoFactory;
import it.gestioneautomobili.service.automobile.AutomobileService;
import it.gestioneautomobili.service.automobile.AutomobileServiceImpl;
import it.gestioneautomobili.service.proprietario.ProprietarioService;
import it.gestioneautomobili.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {
	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}
}
