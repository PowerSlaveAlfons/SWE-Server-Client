package BIF.SWE1;

import java.io.InputStream;
import java.time.LocalDate;

import BIF.SWE1.Plugins.TemperaturePlugin;
import BIF.SWE1.Plugins.ToLowerPlugin;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;

public class UEB6 {

	public void helloWorld() {

	}

	public Request getRequest(InputStream inputStream) {
		return new RequestManager(inputStream);
	}

	public PluginManager getPluginManager() {
		return new PluginManagerCreator();
	}

	public Plugin getTemperaturePlugin() {
		return new TemperaturePlugin();
	}

	public Plugin getNavigationPlugin() {
		return null;
	}

	public Plugin getToLowerPlugin() {
		return new ToLowerPlugin();
	}

	public String getTemperatureUrl(LocalDate localDate, LocalDate localDate1) {
		System.out.println("Temp/" + localDate + "/" + localDate1);
		return "Temp/" + localDate + "/" + localDate1;
	}

	public String getTemperatureRestUrl(LocalDate localDate, LocalDate localDate1) {
		return null;
	}

	public String getNaviUrl() {
		return null;
	}

	public String getToLowerUrl() {
		return "ToLower";
	}
}
