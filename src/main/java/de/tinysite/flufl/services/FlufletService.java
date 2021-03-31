package de.tinysite.flufl.services;

import java.util.List;

public interface FlufletService {

    List<String> getFlufletsInDir(String fluflDirPath);

    void installFluflet(String flufletName);
    void uninstallFluflet(String flufletName);

    void runFluflet(String flufletName);

    String getConfigValue(String fluflet, String value);
}