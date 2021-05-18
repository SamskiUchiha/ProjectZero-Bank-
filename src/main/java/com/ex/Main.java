package com.ex;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    /**
     * Main that runs the program
     * setting the mongodb logging off in the console
     * @param args not sure what this do
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);

        AbstractApp app = new BankApp();
        app.run(args);
    }

}
