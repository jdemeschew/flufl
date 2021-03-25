package de.tinysite.flufl.services;

import java.util.List;

public interface FlufletService {

    List<String> getFlufletsInDir(String fluflDirPath);

    void installFluflet(String flufletName);

}
