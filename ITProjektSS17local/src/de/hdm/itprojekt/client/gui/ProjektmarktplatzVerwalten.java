package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * GUI Klasse die das VerticalPanel vererbt bekommt für Verwalten von
 * Projektmarktplätzen
 * 
 * @author Moritz Bittner
 *
 */
public class ProjektmarktplatzVerwalten extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	// Erstellen von Buttons
	private Button projektmarktplatzAnlegenButton = new Button("Anlegen", new NavigationsButtonHandler());
	private Button projektmarktplatzAndernButton = new Button("Bearbeiten", new NavigationsButtonHandler());
	private Button projektmarktplatzLoschenButton = new Button("Löschen", new NavigationsButtonHandler());

	/**
	 * Konstruktor, der Widgets anfügt
	 */
	public ProjektmarktplatzVerwalten() {
		RootPanel.get("Content").add(new HTML("<h2>Projektmarktplatz verwalten</h2>"));
		mainPanel.add(projektmarktplatzAnlegenButton);
		mainPanel.add(projektmarktplatzAndernButton);
		mainPanel.add(projektmarktplatzLoschenButton);
	}

	/**
	 * Clickhandler der Clcikevents abfängt und je nach click weiterleitet
	 *
	 */
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			Button active = (Button) event.getSource();

			switch (active.getText()) {
			case "Anlegen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektmarktplatzAnlegen());
				break;
			case "Bearbeiten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektmarktplatzBearbeiten());
				break;
			case "Löschen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektmarktplatzLoeschen());
				break;
			}
		}
	};
}
