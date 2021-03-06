
package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;

/**
 * Erstellt einen Bericht mit möglichen Bewerbungsvorschlägen für eingeloggten Teilnehmer
 * Teilnehmer
 * 
 * @author Jiayi
 *
 */
public class BewerbungVorschlaege extends SimpleReport {
	public BewerbungVorschlaege() {
		super("Ihre Vorschläge - " + ClientSideSettings.getCurrentUser().getVorname() + " "
				+ ClientSideSettings.getCurrentUser().getNachname());
		// Report Generator
		ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();

		// Callback des Service calls
		final AsyncCallback<List<AusschreibungReport>> initReportVerschlaegeGeneratorCallback = new AsyncCallback<List<AusschreibungReport>>() {
			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
			}

			@Override
			public void onSuccess(List<AusschreibungReport> result) {
				setSize(result.size());
				render(result);
			}
		};
		reportGenerator.getVorschlaege(ClientSideSettings.getCurrentUser().getId(),
				initReportVerschlaegeGeneratorCallback);

	}

	@Override
	protected void setSize(int i) {
		Label test = new Label("Anzahl Berichte: " + i);
		this.add(test);
	}

	private void render(List<AusschreibungReport> vorlaege) {
		CellTable<AusschreibungReport> table = new CellTable<AusschreibungReport>();

		// Spalte für Titel definieren
		TextColumn<AusschreibungReport> titleColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return ausschreibung.getTitel();
			}
		};

		// Spalte für Bewerbungsfrist definieren
		TextColumn<AusschreibungReport> bewerbungsfristColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {

				DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy");
				return dtf.format(ausschreibung.getBewerbungsfrist());
			}
		};

		// Spalte für Ansprechparnet definieren
		TextColumn<AusschreibungReport> ansprechpartnerColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return ausschreibung.getAnsprechpartnerName();
			}
		};

		// Spalte für Projektname definieren
		TextColumn<AusschreibungReport> projektColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return ausschreibung.getProjektName();
			}
		};

		// Spalten einen Namen geben
		table.addColumn(titleColumn, "Titel");
		table.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
		table.addColumn(ansprechpartnerColumn, "Ansprechpartner");
		table.addColumn(projektColumn, "Projekt");

		// Daten der Tabelle hinzufügen
		table.setRowCount(vorlaege.size(), true);
		table.setRowData(0, vorlaege);

		this.add(table);
	}

}