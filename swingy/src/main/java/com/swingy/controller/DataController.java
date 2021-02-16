package com.swingy.controller;



import com.swingy.model.characters.PrintStats;
import com.swingy.model.storage.Storage;
import com.swingy.model.Navigation;

import java.io.IOException;

public class DataController {
    Storage storage;
    PrintStats stats;
    Navigation navigation;

    public DataController(String name) throws  IOException {
        this.storage = new Storage(name);
        this.stats = new PrintStats(name);
        this.navigation = new Navigation();
    }

        public Storage getStorage() {
            return storage;
        }
        public PrintStats getStats() {
            return stats;
        }
        public Navigation getNavigation() {
            return navigation;
        }
}
