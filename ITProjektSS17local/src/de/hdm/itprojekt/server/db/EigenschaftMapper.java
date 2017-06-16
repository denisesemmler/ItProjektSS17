package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * p> Mapper-Klasse zur Abbildung von <code>Eigenschaft</code> Objekten auf die
 * Datenbank. Ueber das Mapping koennen sowohl Objekte als auch deren Attribute
 * in die Datenbank geschrieben werden, als auch von der Datenbank ausgelesen
 * werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, aendern, Loeschen und Ausgeben von
 * Eigenschaften * bereitgestellt.
 * </p>
 * 
 * @author Marko
 *
 */

public class EigenschaftMapper {

	/**
	 * Die statische Variable eigenschaftMapper stellt sicher, dass es von der
	 * Klasse EigenschaftMapper nur eine einzige Instanz gibt bzw. die Variable
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @author Marko
	 */

	private static EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Der private Konstruktor verhindert, dass eine Instanz der Klasse
	 * EigenschaftMapper per <code>new</code> erzeugt werden kann.
	 */

	private EigenschaftMapper() {
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}

		return eigenschaftMapper;
	}

	/**
	 * Methode die eine Eigenschaft einfügt
	 * 
	 * @param Eigenschaft
	 *            e
	 */

	public Eigenschaft insert(Eigenschaft e) {

		// Connection zur DB auf
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Bisher größte EigenschaftsID bestimmen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Eigenschaft");

			if (rs.next()) {
				// Abgefragte EigenschaftsID++
				e.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// In DB einfuegen
				stmt.executeUpdate("INSERT INTO Eigenschaft (id, name, wert)" + "VALUES (" + e.getId() + "','"
						+ e.getName() + "')");

			}
		}

		catch (SQLException s) {
			s.printStackTrace();
		}

		return e;
	}

	public Eigenschaft update(Eigenschaft e) {

		// Connection zur DB auf
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Eigenschaft SET name='" + e.getName() + "'wert='" + e.getWert() + "'WHERE id='"
					+ e.getId() + "'");

		}

		catch (SQLException s) {
			s.printStackTrace();
		}

		return e;
	}

	
	//Eigenschaften werden anhand ihrer einzigartigen ID gesucht
	public Vector<Eigenschaft> findByProfil(Profil suchProfil) {

		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
		Vector<Eigenschaft> result = new Vector<Eigenschaft>();
		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Eigenschaft " + "WHERE Profil_idProfil = " + suchProfil.getIdProfil());
			// Bei Treffer
			while (rs.next()) {
				// Neues Eigenschaft Objekt erzeugen
				Eigenschaft e = new Eigenschaft(rs.getString("name"), rs.getString("wert"));
				result.add(e);
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

	public void delete(Eigenschaft e) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			stmt.executeUpdate("DELETE FROM Eigenschaft WHERE idEigenschaft = " + e.getIdEigenschaft());
		}
		// Error Handling
		catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
