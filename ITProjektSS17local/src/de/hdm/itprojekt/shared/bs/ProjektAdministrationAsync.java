package de.hdm.itprojekt.shared.bs;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

public interface ProjektAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum,
			AsyncCallback<Projekt> callback);

	void updateProjekt(Projekt p, AsyncCallback<Void> callback);

	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);

	void createTeilnehemr(String name, String zusatz, String emailAdresse, int rolle,
			AsyncCallback<Teilnehmer> callback);
	
	

}
