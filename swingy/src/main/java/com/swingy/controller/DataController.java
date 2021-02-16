package com.swingy.controller;

import java.io.IOException;

public class DataController {
    Storage storage;
    PrintStats stats;
    Navigate navigate;

    public DataController(String name) throws IOException {
        this.storage    = new Storage(name);
        this.stats      = new PrintStats(name);
        this.navigate   = new Navigate();
    }

    public Storage getStorage() {
        return storage;
    }

    public PrintStats getStats() {
        return stats;
    }

    public Navigate getNavigate() {
        return navigate;
    }
}
