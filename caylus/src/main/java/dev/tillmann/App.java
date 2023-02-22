package dev.tillmann;

import dev.tillmann.Caylus.Caylus;
import dev.tillmann.Caylus.Config;

public final class App {
    public static void main(String[] args) {
        Config config = new Config();
        Caylus caylus = new Caylus(config);
    }
}
