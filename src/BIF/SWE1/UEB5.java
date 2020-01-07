package BIF.SWE1;

import java.io.InputStream;

import BIF.SWE1.Plugins.StaticFilePlugin;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;

public class UEB5 {

	public void helloWorld() {

	}

	public Request getRequest(InputStream inputStream) {
		return new RequestManager(inputStream);
	}

	public PluginManager getPluginManager() {
		return new PluginManagerCreator();
	}

	public Plugin getStaticFilePlugin() {
		return new StaticFilePlugin();
	}

	public void setStatiFileFolder(String s) {

	}

	public String getStaticFileUrl(String s) {
		return "statics/tmp-static-files/" + s;
	}
}
