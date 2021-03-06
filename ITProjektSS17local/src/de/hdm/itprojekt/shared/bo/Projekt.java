package de.hdm.itprojekt.shared.bo;

import java.util.Date;

/**
 * Umsetzung der Projektklasse. Als Attribute dienen idProjekt, idTeilnehmer und
 * idProjekt Projektname, Projektbeschreibung, Startdatum und Enddatum
 * 
 * @author denissemmler
 *
 */

public class Projekt extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	private String name = "";
	private String beschreibung = "";
	private Date startDatum = null;
	private Date endDatum = null;
	private int Projektmarktplatz_idProjektmarktplatz = 0;

	/**
	 * Fremschlüsselbeziehung zu Teilnehmer
	 */
	private int Teilnehmer_idTeilnehmer = 0;

	/**
	 * @return the startDatum
	 */
	public Date getStartDatum() {
		return startDatum;
	}

	/**
	 * @param startDatum
	 *            the startDatum to set
	 */
	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	/**
	 * @return the endDatum
	 */
	public Date getEndDatum() {
		return endDatum;
	}

	/**
	 * @param endDatum
	 *            the endDatum to set
	 */
	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	/**
	 * @return the idTeilnehmer
	 */
	public int getTeilnehmer_idTeilnehmer() {
		return Teilnehmer_idTeilnehmer;
	}

	/**
	 * @param idTeilnehmer
	 *            the idTeilnehmer to set
	 */
	public void setTeilnehmer_idTeilnehmer(int teilnehmer_idTeilnehmer) {
		Teilnehmer_idTeilnehmer = teilnehmer_idTeilnehmer;
	}

	/**
	 * @return the idProjekt
	 */

	/**
	 * @return the name
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
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung
	 *            the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the projektmarktplatz_idProjektmarkplatz
	 */
	public int getProjektmarktplatz_idProjektmarktplatz() {
		return Projektmarktplatz_idProjektmarktplatz;
	}

	/**
	 * @param projektmarktplatz_idProjektmarkplatz
	 *            the projektmarktplatz_idProjektmarkplatz to set
	 */
	public void setProjektmarktplatz_idProjektmarktplatz(int projektmarktplatz_idProjektmarktplatz) {
		Projektmarktplatz_idProjektmarktplatz = projektmarktplatz_idProjektmarktplatz;
	}

}