package BIF.SWE1.interfaces;

public interface Plugin {
	/**
	 * Returns a score between 0 and 1 to indicate that the plugin is willing to
	 * handle the request. The plugin with the highest score will execute the
	 * request.
	 * 
	 * @param Request The Http Request
	 * @return A score between 0 and 1
	 */
	float canHandle(Request req);

	/**
	 * Called by the server when the plugin should handle the request.
	 * 
	 * @param The http Request
	 * @return A new response object.
	 */
	Response handle(Request req);
}
