package de.hdm.itprojekt.shared.bs;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * Dieses Interface definiert den Remote Service für ProjektAdministration
 * 
 * @author Patricia
 *
 */

/*
 * diese Annotation definiert denn relativen Pfad zum Service, sprich unter der
 * URL /itprojektss17local/projektadministration der entsprechende URL muss in
 * den Pfad der Web xml hinterlegt werden.
 */
@RemoteServiceRelativePath("projektadministration")
public interface ProjektAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Muss direkt nach der Instanzierung gerufen
	 * werden.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	/*
	 * Methoden zum Anlegen eines Projektmarktplatz
	 */
	public Projektmarktplatz createProjektmarktplatz(String projektmarktplatzBez, int idTeilnehmer)
			throws IllegalArgumentException;

	public void updateProjektmarktplatz(Projektmarktplatz pm) throws IllegalArgumentException;

	public void deleteProjektmarktplatz(Projektmarktplatz pm) throws IllegalArgumentException;

	public Vector<Projekt> findProjekteByProjektmarktplatzId(int projektmarktplatzId) throws IllegalArgumentException;

	public Vector<Projektmarktplatz> findAllProjektmarktplatz() throws IllegalArgumentException;

	public Vector<Projektmarktplatz> findProjektmarktplatzByTeilnehmerId(int teilnehmerId)
			throws IllegalArgumentException;

	/*
	 * Methode zum anlegen eines Projekts
	 */
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum,
			int TeilnehmerID, int MarktplatzID) throws IllegalArgumentException;

	public void updateProjekt(Projekt p) throws IllegalArgumentException;

	public void deleteProjekt(Projekt p) throws IllegalArgumentException;

	public Vector<Ausschreibung> findAusschreibungByProjektId(int projektId) throws IllegalArgumentException;

	public Vector<Projekt> findAllProjektByTeilnehmerId(int teilnehmerId) throws IllegalArgumentException;

	/*
	 * Methoden zum anlegen von Ausschreibungen
	 */
	public Ausschreibung createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel, String status,
			int projekt_idProjekt, int profil_idSuchprofil, int teilnehmer_idTeilnehmer)
			throws IllegalArgumentException;

	public void updateAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	public Vector<Bewerbung> findBewerbungenByAusschreibungId(int AuscchreibungId) throws IllegalArgumentException;

	public Vector<Ausschreibung> findAllAusschreibungByTeilnehmerId(int teilnehmerId) throws IllegalArgumentException;

	public Vector<Ausschreibung> findAllAusschreibungen() throws IllegalArgumentException;

	/*
	 * Methoden zum anlegen von Bewerbungen
	 */
	public Bewerbung createBewerbung(String bewerbungstext, Date erstelldatum, float bewertung, String status,
			String titel, int profil_idProfil, int ausschreibung_idAusschreibung) throws IllegalArgumentException;

	public void updateBewerbung(Bewerbung b) throws IllegalArgumentException;

	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException;

	public Vector<Bewerbung> findBewerbungByTeilnehmerid(int tilnehmerId) throws IllegalArgumentException;

	public Bewerbung findBewerbungByProfilIdAndAusschreibungId(int id, int ausschreibungID)
			throws IllegalArgumentException;

	public void bewertungZurBewerbung(int bewerbungId, float bewertung, String stellungnahme, int projektId,
			int manntage, Date startdatum, Date enddatum) throws IllegalArgumentException;

	/*
	 * Methoden zum anlegen von Teilnehmern
	 */

	public Teilnehmer login(String requestUri);

	/*
	 * Methoden zum anlegen von Teilnehmern
	 */
	public Teilnehmer createTeilnehmer(String vorname, String nachname, String zusatz, String strasse, int plz,
			String ort, String emailAdresse, String firma) throws IllegalArgumentException;

	public void updateTeilnehmer(Teilnehmer t) throws IllegalArgumentException;

	public Profil createProfil(int teilnehmerId, int suchprofil) throws IllegalArgumentException;

	public Profil getProfilIdCurrentUser(int teilnehmerId) throws IllegalArgumentException;

	public Teilnehmer findTeilnehmerByBewerbungId(int bewerbungId) throws IllegalArgumentException;

	/*
	 * Methoden f�r Eigenschaften
	 */

	public Vector<Eigenschaft> createEigenschaft(Vector<String> name, Vector<Integer> wert, int teilnehmerId)
			throws IllegalArgumentException;

	public Vector<Eigenschaft> findNameAndWertFromEigenschaften(int profilId) throws IllegalArgumentException;

	void updateEigenschaft(Vector<Eigenschaft> eigenschaften) throws IllegalArgumentException;

	Vector<Ausschreibung> matchingAusschreibung(int profilUserId) throws IllegalArgumentException;

	void updateProfil(Profil p) throws IllegalArgumentException;
}
