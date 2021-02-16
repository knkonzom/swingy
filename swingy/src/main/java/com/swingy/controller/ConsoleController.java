package com.swingy.controller;

import java.io.IOException;

import com.swingy.view.ConsoleView;

public class ConsoleController {
    ConsoleView viewConsole;

    public ConsoleController() throws IOException {
        viewConsole = new ConsoleView();
    }
}
