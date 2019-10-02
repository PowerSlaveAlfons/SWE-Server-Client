package BIF.SWE1;

import java.io.InputStream;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;
import BIF.SWE1.URLManager;

public class UEB2 {

	public void helloWorld() {

	}

	public Url getUrl(String s) {
	    return new URLManager(s);
	}

	public Request getRequest(InputStream inputStream) {
		return new RequestManager();
	}

	public Response getResponse() {
		return new ResponseManager();
	}
}
