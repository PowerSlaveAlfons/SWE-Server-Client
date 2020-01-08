package BIF.SWE1.interfaces;

import java.lang.Iterable;
import java.util.List;

public interface PluginManager {
    /**
     * Returns a list of all plugins. Never returns null;
     * @return List of all loaded Plugins
     */
	List<Plugin> getPlugins();
	
	
    /**
     * Adds a new plugin. If the plugin was already added, nothing will happen.
     * @param plugin The Plugin that wants to be added
     */
    void add(Plugin plugin);
    
    /**
     * Adds a new plugin by class name. If the plugin was already added, nothing will happen.
     * Throws an exeption, when the type cannot be resoled or the class does not implement Plugin.
     * @param String name of the plugin to be added
     * @throws ClassNotFoundException  if the class has not been found
     * @throws IllegalAccessException if the loader has no access to the file
     * @throws InstantiationException  if something happens
     */
    void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException;


    
    /**
     * Clears all plugins
     */
    void clear();
}
