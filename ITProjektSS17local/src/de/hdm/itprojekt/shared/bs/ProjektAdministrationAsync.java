package de.hdm.itprojekt.shared.bs;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * Dieses Interface dient zur Kommunikation mit der GUI und stellt alle Methoden
 * aus dem RemoteInterface bereit, und bietet für jede Methode ein AsyncCallback
 * Parameter an.
 * 
 * @author Patricia
 *
 */
public interface ProjektAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	/*
	 * F�r Projektmarktpl�tze
	 */
	void createProjektmarktplatz(String projektmarktplatzBez, int idTeilnehmer,
			AsyncCallback<Projektmarktplatz> callback);

	void updateProjektmarktplatz(Projektmarktplatz pm, AsyncCallback<Void> callback);

	void deleteProjektmarktplatz(Projektmarktplatz pm, AsyncCallback<Void> callback);

	void findProjekteByProjektmarktplatzId(int projektmarktplatzId, AsyncCallback<Vector<Projekt>> callback);

	void findAllProjektmarktplatz(AsyncCallback<Vector<Projektmarktplatz>> callback);

	void findProjektmarktplatzByTeilnehmerId(int teilnehmerId, AsyncCallback<Vector<Projektmarktplatz>> callback);

	/*
	 * F�r Projekte
	 */
	void createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum, int TeilnehmerID,
			int MarktplatzID, AsyncCallback<Projekt> callback);

	void updateProjekt(Projekt p, AsyncCallback<Void> callback);

	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);

	void findAusschreibungByProjektId(int projektId, AsyncCallback<Vector<Ausschreibung>> callback);

	void findAllProjektByTeilnehmerId(int teilnehmerId, AsyncCallback<Vector<Projekt>> callback);

	void createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel, String status,
			int projekt_idProjekt, int profil_idSuchprofil, int teilnehmer_idTeilnehmer,
			AsyncCallback<Ausschreibung> callback);

	void updateAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void findBewerbungenByAusschreibungId(int AusschreibungId, AsyncCallback<Vector<Bewerbung>> callback);

	void findAllAusschreibungByTeilnehmerId(int teilnehmerId, AsyncCallback<Vector<Ausschreibung>> callback);

	void findAllAusschreibungen(AsyncCallback<Vector<Ausschreibung>> callback);

	/*
	 * F�r Bewerbungen
	 */
	void createBewerbung(String bewerbungstext, Date erstelldatum, float bewertung, String status, String titel,
			int profil_idProfil, int ausschreibung_idAusschreibung, AsyncCallback<Bewerbung> callback);

	void updateBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	void deleteBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	void findBewerbungByTeilnehmerid(int teilnehmerId, AsyncCallback<Vector<Bewerbung>> callback);

	void findBewerbungByProfilIdAndAusschreibungId(int id, int ausschreibungID, AsyncCallback<Bewerbung> callback);

	void bewertungZurBewerbung(int bewerbungId, float bewertung, String stellungnahme, int projektId, int manntage,
			Date startdatum, Date enddatum, AsyncCallback<Void> callback);

	/*
	 * F�r Teilnehmer
	 */
	void login(String requestUri, AsyncCallback<Teilnehmer> callback);

	void createTeilnehmer(String vorname, String nachname, String zusatz, String strasse, int plz, String ort,
			String emailAdresse, String firma, AsyncCallback<Teilnehmer> callback);

	void updateTeilnehmer(Teilnehmer t, AsyncCallback<Void> callback);

	void createProfil(int teilnehmerId, int suchprofil, AsyncCallback<Profil> callback);

	void getProfilIdCurrentUser(int teilnehmerId, AsyncCallback<Profil> callback);

	void findTeilnehmerByBewerbungId(int bewerbungId, AsyncCallback<Teilnehmer> callback);

	/*
	 * Für Eigenschaften
	 */
	void findNameAndWertFromEigenschaften(int profilId, AsyncCallback<Vector<Eigenschaft>> callback);

	void createEigenschaft(Vector<String> name, Vector<Integer> wert, int teilnehmerId,
			AsyncCallback<Vector<Eigenschaft>> callback);

	void updateEigenschaft(Vector<Eigenschaft> eigenschaften, AsyncCallback<Void> callback);

	void matchingAusschreibung(int profilUserId, AsyncCallback<Vector<Ausschreibung>> callback);

	void updateProfil(Profil p, AsyncCallback<Void> callback);

}
