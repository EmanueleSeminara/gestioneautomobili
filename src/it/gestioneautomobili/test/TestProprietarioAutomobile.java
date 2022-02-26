package it.gestioneautomobili.test;

import java.util.Date;
import java.util.List;

import it.gestioneautomobili.dao.EntityManagerUtil;
import it.gestioneautomobili.model.Automobile;
import it.gestioneautomobili.model.Proprietario;
import it.gestioneautomobili.service.MyServiceFactory;
import it.gestioneautomobili.service.automobile.AutomobileService;
import it.gestioneautomobili.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {
		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciAutomobile(proprietarioService, automobileService);
			System.out.println(
					"In tabella Proprietario ci sono " + automobileService.listAllAutomobili().size() + " elementi.");

			testRimozioneAutomobile(proprietarioService, automobileService);
			System.out.println(
					"In tabella Proprietario ci sono " + automobileService.listAllAutomobili().size() + " elementi.");

			testRimozioneProprietario(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testAggiornamentoAutomobile(proprietarioService, automobileService);
			System.out.println(
					"In tabella Proprietario ci sono " + automobileService.listAllAutomobili().size() + " elementi.");

			testAggiornamentoProprietario(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
		// creo nuovo proprietario
		Proprietario nuovoProprietario = new Proprietario("Luca", "Neri", "asd123", new Date());
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
	}

	private static void testInserisciAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");

		// creo nuovo automobile ma prima mi serve un proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Pluto", "Plutorum", "Plutante", 1990);
		// lo lego al primo proprietario che trovo
		nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo automobile
		automobileService.inserisciNuovo(nuovaAutomobile);

		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		// il test fallisce anche se non è riuscito a legare i due oggetti
		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il proprietario ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
	}

	private static void testRimozioneAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testRimozioneAutomobile inizio.............");

		// inserisco un automobile che rimuoverò
		// creo nuovo automobile ma prima mi serve un proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testRimozioneAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Pluto", "Plutorum", "Plutante", 1990);
		// lo lego al primo proprietario che trovo
		nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo automobile
		automobileService.inserisciNuovo(nuovaAutomobile);

		Long idAutomobileInserito = nuovaAutomobile.getId();
		automobileService.rimuovi(automobileService.caricaSingolaAutomobile(idAutomobileInserito));
		// proviamo a vedere se è stato rimosso
		if (automobileService.caricaSingolaAutomobile(idAutomobileInserito) != null)
			throw new RuntimeException("testRimozioneAutomobile fallito: record non cancellato ");
		System.out.println(".......testRimozioneAutomobile fine: PASSED.............");
	}

	private static void testRimozioneProprietario(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testRimozioneProprietario inizio.............");

		Proprietario nuovoProprietario = new Proprietario("Luca", "Neri", "asd123", new Date());
		// lo lego al primo proprietario che trovo

		// salvo il nuovo proprietario
		proprietarioService.inserisciNuovo(nuovoProprietario);

		Long idProprietarioInserito = nuovoProprietario.getId();
		proprietarioService.rimuovi(proprietarioService.caricaSingoloProprietario(idProprietarioInserito));
		// proviamo a vedere se è stato rimosso
		if (proprietarioService.caricaSingoloProprietario(idProprietarioInserito) != null)
			throw new RuntimeException("testRimozioneProprietario fallito: record non cancellato ");
		System.out.println(".......testRimozioneProprietario fine: PASSED.............");
	}

	private static void testAggiornamentoAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testAggiornamentoAutomobile inizio.............");

		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testRimozioneAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Pluto", "Plutorum", "Plutante", 1990);
		// lo lego al primo proprietario che trovo
		nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo automobile
		automobileService.inserisciNuovo(nuovaAutomobile);

		nuovaAutomobile.setMarca("Paperino");

		automobileService.aggiorna(nuovaAutomobile);

		if (!nuovaAutomobile.getMarca().equals("Paperino")) {
			throw new RuntimeException("testAggiornamentoAutomobile fallito: record non aggiornato ");
		}

		automobileService.rimuovi(nuovaAutomobile);

		System.out.println(".......testAggiornamentoAutomobile fine: PASSED.............");

	}

	private static void testAggiornamentoProprietario(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testAggiornamentoProprietario inizio.............");

		Proprietario nuovoProprietario = new Proprietario("Luca", "Neri", "asd123", new Date());
		// lo lego al primo proprietario che trovo

		// salvo il nuovo proprietario
		proprietarioService.inserisciNuovo(nuovoProprietario);

		nuovoProprietario.setCongome("Paperino");

		proprietarioService.aggiorna(nuovoProprietario);

		if (!nuovoProprietario.getCongome().equals("Paperino")) {
			throw new RuntimeException("testAggiornamentoProprietario fallito: record non aggiornato ");
		}

		proprietarioService.rimuovi(nuovoProprietario);

		System.out.println(".......testAggiornamentoProprietario fine: PASSED.............");

	}

}
