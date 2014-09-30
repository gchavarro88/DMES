import java.io.Serializable;


public class DefaultTabsViews implements Serializable {

	
	private String nameTab;
	private String urlTab;
	private boolean visible;
	
	public DefaultTabsViews(String nameTab, String url, boolean visible) {

		this.nameTab = nameTab;
		this.urlTab = url;
		this.visible = visible;
	}

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
