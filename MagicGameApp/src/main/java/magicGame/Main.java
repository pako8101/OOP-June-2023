package magicGame;

import magicGame.core.Controller;
import magicGame.core.ControllerImpl;
import magicGame.core.Engine;
import magicGame.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
