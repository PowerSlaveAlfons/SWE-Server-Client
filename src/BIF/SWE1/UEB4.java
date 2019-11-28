package BIF.SWE1;

import java.io.InputStream;

import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class UEB4 {

	public void helloWorld() {
	}

	public Request getRequest(InputStream inputStream) {
		return new RequestManager(inputStream);
	}

	public Response getResponse() {
		return new ResponseManager();
	}

	public PluginManager getPluginManager() {
		return new PluginManagerCreator();
	}
}
