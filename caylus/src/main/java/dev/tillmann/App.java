package dev.tillmann;

import dev.tillmann.caylus.Caylus;
import dev.tillmann.caylus.Config;

public final class App {
    public static void main(String[] args) {
        Config config = new Config();
        Caylus caylus = new Caylus(config);
        caylus.start();
    }
}
