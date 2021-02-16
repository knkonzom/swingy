package com.swingy.controller;

import java.io.IOException;
import com.swingy.view.ConsoleView;

public class Controller {
    ConsoleView displayConsole;

    public Controller() throws IOException {
        displayConsole = new ConsoleView();
    }
}
