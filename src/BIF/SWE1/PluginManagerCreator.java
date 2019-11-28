package BIF.SWE1;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;

public class PluginManagerCreator implements PluginManager {
    @Override
    public Iterable<Plugin> getPlugins() {
        return null;
    }

    @Override
    public void add(Plugin plugin) {

    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

    }

    @Override
    public void clear() {

    }
}
