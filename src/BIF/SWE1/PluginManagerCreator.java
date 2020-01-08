package BIF.SWE1;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the PluginManager interface
 */
public class PluginManagerCreator implements PluginManager {


    private List<Plugin> Plugins = new ArrayList<>();
    private List<String> Defaults;
    private static String pack = "BIF.SWE1.Plugins.";
    private static String nameSuffix = "Plugin.java";

    private File directory = new File("src" + File.separatorChar + pack.replace('.', File.separatorChar));
    private FilenameFilter filter = (dir, name) -> name.endsWith(nameSuffix);

    /**
     * Constructor, needed to collect and "mount" the plugins
     */
    PluginManagerCreator()
    {
        Defaults = gatherPlugins();
        // adding all default Plugins one-by-one
        add("AllElseFailsPlugin");
        add("NaviPlugin");
        add("TemperaturePlugin");
        add("ToLowerPlugin");
        add("StaticFilePlugin");
        add("TestPlugin");
    }

    /**
     * Returns a list of Plugins
     * @return List<Plugin>
     */
    @Override
    public List<Plugin> getPlugins() {
        return this.Plugins;
    }

    /**
     * Adds a Plugin by object reference
     * @param plugin The Plugin that wants to be added
     */
    @Override
    public void add(Plugin plugin) {
        this.Plugins.add(plugin);
    }

    /**
     * Adds a Plugin by name
     * @param plugin
     */
    @Override
    public void add(String plugin) {
        // check if searched plugin is available
        if (Defaults.contains(plugin)) {
            ClassLoader loader = Plugin.class.getClassLoader();
            try {
                // argument is the classpath to the requested plugin
                Class myClass = loader.loadClass(pack + plugin);
                Object object = myClass.getDeclaredConstructor().newInstance();
                Plugins.add((Plugin) object);
                System.out.println("Added " + object + " to plugins. Now " + this.Plugins.size() + " plugins are loaded.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException(plugin + " not found!");
        }
    }

    private List<String> gatherPlugins()
    {
        List<String> foundPlugins = new ArrayList<>();
        String[] pluginNames = directory.list(filter);
        if (pluginNames == null)
        {
            System.out.println("WARNING: No plugins found!");
            return null;
        }
        else
        {
            for (String name : pluginNames)
            {
                name = name.substring(0, name.indexOf("."));
                foundPlugins.add(name);
            }
            return foundPlugins;
        }
    }

    /**
     * Finds the Plugin that returns the highest canHandle score in order to let that plugin do the work
     * @param request The HTTP Request
     * @return Plugin
     */
    public Plugin getBestHandlePlugin(Request request) {
        float maxHandleValue = 0.0f;
        Plugin suitablePlugin = null;
        for (Plugin plugin : Plugins) {
            float handleValue = plugin.canHandle(request);
            System.out.println("checking " + plugin);
            if (handleValue > maxHandleValue) {
                maxHandleValue = handleValue;
                suitablePlugin = plugin;
            }
        }
        return suitablePlugin;
    }

    /**
     * Clears all mounted Plugins
     */
        @Override
    public void clear() {
        this.Plugins.clear();
    }
}
