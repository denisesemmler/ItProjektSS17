package de.hdm.itprojekt.shared.bo;

/**
 * Umsetzung der Projektmarktplatzklasse. Als Attribute dienen ID und Bezeichnung
 * @author denisesemmler
 */

public class Projektmarktplatz extends BusinessObjekt {
	
	private static final long serialVersionUID = 1L;
	private int idProjektmarktplatz = 0; 
	private String Bezeichnung = "";
	private int Teilnehmer_idTeilnehmer = 0;
	
	/**
	 * @return  bezeichnung
	 */
	public String getBezeichnung() {
		return Bezeichnung;
	}

	/**
	 * @param bezeichnung die bezeichnung festlegen
	 */
	public void setBezeichnung(String bezeichnung) {
		Bezeichnung = bezeichnung;
	}

	/**
	 * @return the idProjektmarktplatz
	 */
	public int getIdProjektmarktplatz() {
		return idProjektmarktplatz;
	}

	/**
	 * @param idProjektmarktplatz the idProjektmarktplatz to set
	 */
	public void setIdProjektmarktplatz(int idProjektmarktplatz) {
		this.idProjektmarktplatz = idProjektmarktplatz;
	}

	/**
	 * @return the teilnehmer_idTeilnehmer
	 */
	public int getTeilnehmer_idTeilnehmer() {
		return Teilnehmer_idTeilnehmer;
	}

	/**
	 * @param teilnehmer_idTeilnehmer the teilnehmer_idTeilnehmer to set
	 */
	public void setTeilnehmer_idTeilnehmer(int teilnehmer_idTeilnehmer) {
		Teilnehmer_idTeilnehmer = teilnehmer_idTeilnehmer;
	}

}




