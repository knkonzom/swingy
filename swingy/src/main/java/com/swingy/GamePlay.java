package com.swingy;

import com.swingy.controller.Controller;

import java.io.IOException;

public class GamePlay {

    public static void main(String[] args) throws IOException {
        try
        {
            if (args[0].equalsIgnoreCase("Console") && args.length == 1) {
                new Controller();
            }
            else if (args.length != 1) {
                System.out.println("Incorrect Usage!");
            }
            else
            {
                System.out.println("USAGE: java - jar target/swingy-1.0.jar [console]");
                System.exit(1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("USAGE: java -jar target/swingy-1.0.jar [console]");
            System.exit(1);
        }
    }
}
