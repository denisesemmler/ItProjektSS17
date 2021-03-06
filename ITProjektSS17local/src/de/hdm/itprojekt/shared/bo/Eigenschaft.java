package de.hdm.itprojekt.shared.bo;

/**
 * Klasse Eighenschaft zur Repräsentation von Eigenschaften eines Profils
 * 
 * @author Denise
 *
 */

public class Eigenschaft extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	private int profil_idProfil;
	/**
	 * Name der Eigenschaft
	 */
	private String name = "";

	/**
	 * Wert der Eigenschaft
	 */
	private int wert = 0;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return wert
	 */
	public int getWert() {
		return wert;
	}

	/**
	 * @param wert
	 *            the wert to set
	 */
	public void setWert(int wert) {
		this.wert = wert;
	}

	/**
	 * @return the profil_idProfil
	 */
	public int getProfil_idProfil() {
		return profil_idProfil;
	}

	/**
	 * @param profil_idProfil
	 *            the profil_idProfil to set
	 */
	public void setProfil_idProfil(int profil_idProfil) {
		this.profil_idProfil = profil_idProfil;
	}

	/**
	 * Konvertiert die technisch gespeicherte id in einen lesbaren String
	 * @return Eigenschafts Wert als lesbarer String
	 */
	public String getWertAsString() {
		if (this.name.equals("Höchster Schulabschluss")) {
			String[] options = { "Hauptschulabschluss", "Mittlere Reife", "Fachhochschulreife", "Abitur", "Bachelor",
					"Master" };
			return options[this.wert];
		} else if (this.name.equals("Berufserfahrung")) {
			String[] options = { "weniger als 1 Jahr", "1 - 5 Jahre", "6 - 10 Jahre", "mehr als 10 Jahre" };
			return options[this.wert];
		} else if (this.name.equals("Microsoft Office")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("Microsoft Project")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("SAP/ERP")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("ARIS")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("Java")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("C/C++")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("CATIA")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		} else if (this.name.equals("SQL/DB")) {
			String[] options = { "Keine Kenntnisse", "Wenig Kenntnisse", "Gute Kenntnisse" };
			return options[this.wert];
		}
		return String.valueOf(this.wert);
	}

}
