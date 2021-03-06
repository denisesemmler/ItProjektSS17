package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

/**
 * Klasse für Anzeigen von Ausschreibungen
 * 
 * @author Moritz Bittner
 *
 */
public class AusschreibungAnzeigen extends VerticalPanel {

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;

	/**
	 * Erstellen der Labels
	 */
	private Label marktplatzLabel = new Label("Projektmarktplatz wählen:");

	/**
	 * Erstellen der ListBox
	 */
	private ListBox marktplatzListbox = new ListBox();
	private ListBox projektListbox = new ListBox();
	private ListBox ausschreibungListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button marktplatzSuchenButton = new Button("Projekte suchen", new SuchenClickHandler());
	private Button projektSuchenButton = new Button("Ausschreibungen suchen", new SuchenProjektClickHandler());
	private Button ausschreibungAnzeigenButton = new Button("Ausschreibung anzeigen",
			new AusschreibungAnzeigenClickHandler());

	// Vektoren erstellen
	private Vector<Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();
	private Vector<Projekt> pVector = new Vector<Projekt>();
	private Vector<Ausschreibung> aVector = new Vector<Ausschreibung>();
	private int pmID;
	private int pID;

	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public AusschreibungAnzeigen() {

		// CSS Styling
		marktplatzLabel.addStyleName("label1");
		marktplatzListbox.addStyleDependentName("label1");
	
		
		// GUI erstellen
		mainPanel.add(marktplatzLabel);
		mainPanel.add(marktplatzListbox);
		mainPanel.add(marktplatzSuchenButton);
		// Alle Marktplätze suchen
		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	/**
	 * Callback für auslesen alle Projektmarktplätze
	 */
	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {
			// Alle Marktplätez anzeigen
			for (int i = 0; i < result.size(); i++) {
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
				marktplatzListbox.addItem(pm1.getBezeichnung());
			}

		}
	}

	/**
	 * Clickhandler, der neue Widgets anfügt und das Suchen von Projekte in
	 * ausgewähltem PM veranlasst
	 */
	private class SuchenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			// Bei Suchen klick, projekte in diesem Markplatz suchen
			pmID = pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getId();
			mainPanel.clear();
			mainPanel.add(marktplatzLabel);
			marktplatzLabel.setText("Projekt wählen");
			marktplatzLabel.addStyleName("label1");
			mainPanel.add(projektListbox);
			projektListbox.addStyleName("label1");
			mainPanel.add(projektSuchenButton);

			try {
				ClientSideSettings.getProjektAdministration().findProjekteByProjektmarktplatzId(pmID,
						new GetAllProjekteByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	/**
	 * Auslesen aller Projekte by Projektmarktplatz ID
	 */
	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");
		}

		// Gefundene Projekt anzeigen
		public void onSuccess(Vector<Projekt> result) {

			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}

		}
	}

	/**
	 * Neue Widgets für Suchen einer Ausschreibung
	 */
	private class SuchenProjektClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			// Ausschreibungen des Projekts anzeigen
			pID = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
			mainPanel.clear();
			mainPanel.add(marktplatzLabel);
			marktplatzLabel.setText("Ausschreibung suchen:");
			marktplatzLabel.addStyleName("label1");
			mainPanel.add(ausschreibungListbox);
			ausschreibungListbox.addStyleName("label1");
			mainPanel.add(ausschreibungAnzeigenButton);

			try {
				ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(pID,
						new GetAllAusschreibungByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	/**
	 * Callback für Alle ausschreibungen by Projekt ID
	 */
	private class GetAllAusschreibungByIDCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");
		}

		public void onSuccess(Vector<Ausschreibung> result) {
			// Gefundene Ausschreibungen in ListBox laden
			for (int i = 0; i < result.size(); i++) {
				Ausschreibung a1 = result.elementAt(i);
				aVector.add(a1);
				ausschreibungListbox.addItem(a1.getTitel());
				
			}

		}
	}

	/**
	 * Clickhandler, der Ausschreibung anzeigt und auf Click zu der
	 * Ausschreibung weiterleitet
	 */
	private class AusschreibungAnzeigenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(pID,
						new GetAllAusschreibungByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
			// Anzuzeigende Ausschreibung in Ausschreibungs Objekt laden
			Ausschreibung a = new Ausschreibung();
			a.setId(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getId());
			a.setBeschreibung(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getBeschreibung());
			a.setTitel(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getTitel());
			a.setBewerbungsfrist(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getBewerbungsfrist());
			a.setProfil_idSuchprofil(
					aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getProfil_idSuchprofil());
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new EinzelAusschreibung(a));

		}
	};

}