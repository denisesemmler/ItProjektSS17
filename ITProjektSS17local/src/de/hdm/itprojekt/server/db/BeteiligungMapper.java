/**
 * 
 */
package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.server.ServersideSettings;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;

/**
 * p> Mapper-Klasse zur Abbildung von <code>Beteiligung</code> Objekten auf die
 * Datenbank. über das Mapping können sowohl Objekte als auch deren Attribute in
 * die Datenbank geschrieben werden, als auch von der Datenbank ausgelesen
 * werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, ändern, Löschen und Ausgeben von
 * Beteiligungen * bereitgestellt.
 * </p>
 * 
 * @author Denise
 *
 */
public class BeteiligungMapper {

	private static BeteiligungMapper beteiligungMapper = null;

	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen mittels des
	 * <code>new</code> Keywords.
	 */
	private BeteiligungMapper() {

	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static BeteiligungMapper beteiligungMapper() {
		if (beteiligungMapper == null) {
			beteiligungMapper = new BeteiligungMapper();
		}

		return beteiligungMapper;
	}

	/**
	 * Suche einer Beteiligung anhand seiner einzigartigen ID.
	 * 
	 * @param id
	 *            - Prim�rschl�ssel von Beteiligung
	 * @return Beteiligung Objekt, das die gesuchte ID enth�lt
	 */
	public Beteiligung findById(int id) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM Beteiligung " + "WHERE idBeteiligung = " + id;

			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery(sql);

			ServersideSettings.getLogger().info(sql);

			// Bei Treffer
			if (rs.next()) {

				// Neues Beteiligung Objekt erzeugen
				Beteiligung b = new Beteiligung();

				// Id, stellungname mit den Daten aus der DB f�llen
				b.setId(rs.getInt("idBeteiligung"));
				b.setStellungnahme(rs.getString("stellungnahme"));
				b.setProjektID(rs.getInt("Projekt_idProjekt"));
				b.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
				b.setManntage(rs.getInt("manntage"));
				b.setStartdatum(rs.getDate("startdatum"));
				b.setEnddatum(rs.getDate("enddatum"));

				ServersideSettings.getLogger().info(b.toString());
				// Objekt zur�ckgeben
				return b;
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Falls nichts gefunden wurde null zur�ckgeben
		return null;
	}

	/**
	 * Suche Beteiligungen anhand eines Projektnamens. Da Projekt mehrere
	 * Beteiligungen enthalten k�nnen, k�nnen mehrere Ergebnisse ausgegeben
	 * werden. Alle gefundenen Beteilungen werden in einem Vektor gespeichert.
	 * 
	 * @param projektname
	 *            name des gesuchten Projektes
	 * @return Vektor mit allen zu den Suchparametern gefundenen Beteiligungen
	 */
	public Vector<Beteiligung> findByName(String projektName) {
		// Datenbankverbindung
		Connection con = DBConnection.connection();

		// Ergebnis-ArrayList anlegen
		Vector<Beteiligung> result = new Vector<Beteiligung>();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM Beteiligung " + "WHERE name = '" + projektName + "'";

			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery(sql);

			ServersideSettings.getLogger().info(sql);

			// Für jeden gefundenen Treffer...
			while (rs.next()) {
				// ... neues User Objekt anlegen
				Beteiligung b = new Beteiligung();

				// ... Id, Projetname, Bewertung und Beschreibung mit den Daten
				// aus der DB f�llen
				b.setId(rs.getInt("idBeteiligung"));
				b.setProjektID(rs.getInt("Projekt_idProjekt"));
				b.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
				b.setStellungnahme(rs.getString("stellungnahme"));
				b.setManntage(rs.getInt("manntage"));
				b.setStartdatum(rs.getDate("startdatum"));
				b.setEnddatum(rs.getDate("enddatum"));

				ServersideSettings.getLogger().info(b.toString());

				// ... Objekt dem Ergebnisvektor hinzufügen
				result.add(b);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis zur�ckgeben
		return result;
	}

	/**
	 * Ausgabe aller Beteiligung Datens�tze
	 * 
	 * @return Vektor mit allen Beteiligung
	 */
	public Vector<Beteiligung> findAllBeteiligung() {

		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		// Ergebnis-ArrayList anlegen
		Vector<Beteiligung> result = new Vector<Beteiligung>();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "SELECT * " + "FROM Beteiligung";

			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery(sql);

			ServersideSettings.getLogger().info(sql);

			// Für jeden Eintrag neues User Objekt erzeugen
			while (rs.next()) {
				Beteiligung b = new Beteiligung();
				b.setId(rs.getInt("idBeteiligung"));
				b.setStellungnahme(rs.getString("stellungnahme"));
				b.setProjektID(rs.getInt("Projekt_idProjekt"));
				b.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
				b.setManntage(rs.getInt("manntage"));
				b.setStartdatum(rs.getDate("startdatum"));
				b.setEnddatum(rs.getDate("enddatum"));

				ServersideSettings.getLogger().info(b.toString());

				// Teilnehmer dem Ergebnisvektor hinzufügen
				result.add(b);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zur�ckgeben
		return result;
	}

	/**
	 * Neue Beteiligung in der Datenbank anlegen.
	 * 
	 * @param b
	 *            Beteiligung Objekt, das in die Datenbank eingef�gt werden soll
	 */
	public Beteiligung insert(Beteiligung b) {

		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "SELECT MAX(idBeteiligung) AS maxId FROM Beteiligung";

			// SQL Query ausf�hren um die h�chste id zu erhalten
			ResultSet rs = stmt.executeQuery(sql);

			ServersideSettings.getLogger().info(sql);

			if (rs.next()) {

				// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
				b.setId(rs.getInt("maxId") + 1);

				// neues SQL Statement
				stmt = con.createStatement();

				String sql2 = "INSERT INTO Beteiligung (idBeteiligung, Bewerbung_idBewerbung, stellungnahme, Projekt_idProjekt, manntage, startdatum, enddatum)"
						+ "VALUES " + "('" + b.getId() + "', '" + b.getBewerbungID() + "', '" + b.getStellungnahme()
						+ "', '" + b.getProjektID() + "', '" + b.getManntage() + "', '" + b.getStartdatum() + "', '"
						+ b.getEnddatum() + "')";

				// SQL Query ausf�hren um Datensatz in DB zu schreiben
				stmt.executeUpdate(sql2);
				ServersideSettings.getLogger().info(sql2);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * Diese Methode l�scht einen Beteiligung in der Datenbank die dazugeh�rigen
	 * Beteiligung-Referenzen in allen Tabellen
	 * 
	 * @param u
	 *            die zu l�schende Beteiligung
	 */
	public void delete(Beteiligung b) {

		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {

			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "DELETE FROM Beteiligung WHERE idBeteiligung = " + b.getId();

			// SQL Query ausf�hren
			stmt.executeUpdate(sql);

			ServersideSettings.getLogger().info(sql);

		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Suche der Beteiligung anhand seiner einzigartigen ID
	public Beteiligung findByBewerbung(Bewerbung b) {

		// Datenbankverbindung
		Connection con = DBConnection.connection();

		try {

			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM Beteiligung " + "WHERE Bewerbung_idBewerbung = " + b.getId();

			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery(sql);

			ServersideSettings.getLogger().info(sql);

			// Für jeden gefundenen Treffer...
			if (rs.next()) {

				// ... neues User Objekt anlegen
				Beteiligung beteiligung = new Beteiligung();

				// ... Id, Projektname, Bewertung und Beschreibung mit den Daten
				// aus der DB fuellen
				beteiligung.setId(rs.getInt("idBeteiligung"));
				beteiligung.setProjektID(rs.getInt("Projekt_idProjekt"));
				beteiligung.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
				beteiligung.setStellungnahme(rs.getString("stellungnahme"));
				beteiligung.setManntage(rs.getInt("manntage"));
				beteiligung.setStartdatum(rs.getDate("startdatum"));
				beteiligung.setEnddatum(rs.getDate("enddatum"));

				// Protokolieren des gelesenen Objekts
				ServersideSettings.getLogger().info(beteiligung.toString());

				return beteiligung;

			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis zur�ckgeben
		return null;
	}

}
