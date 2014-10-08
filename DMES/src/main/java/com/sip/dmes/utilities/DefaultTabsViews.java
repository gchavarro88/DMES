package com.sip.dmes.utilities;

import java.io.Serializable;

/**
 * Proyecto: DMES - Versión: 1.0
 * @author (es): Gustavo Adolfo Chavarro Ortiz 
 * @Funcionalidad: Esta clase está encargada de servir como objeto para un modelo
 * de pestañas en un panel, teniendo como atributos el nombre de la pestaña, la 
 * url de la página a la que hace referencia y un atributo booleano que inidica
 * si s epuede visualizar.
 * @Modificado por:  
 * @Funcionalidad agregada:
 */
public class DefaultTabsViews implements Serializable {

	
	private String nameTab;//Nombre de la pestaña
	private String urlTab; //Página visualizada en la pestaña
	private boolean visible; //Atributo que inidica si es visible
	
        /**
         * Método constructor.
         */
	public DefaultTabsViews(String nameTab, String url, boolean visible) {

		this.nameTab = nameTab;
		this.urlTab = url;
		this.visible = visible;
	}

        /**
         * Métodos getters and setters.
         */
	public String getNameTab() {
		return nameTab;
	}

	public void setNameTab(String nameTab) {
		this.nameTab = nameTab;
	}


	public String getUrlTab() {
		return urlTab;
	}

	public void setUrlTab(String urlTab) {
		this.urlTab = urlTab;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	
}
