package ViceCity.src.main.java.viceCity;

import ViceCity.src.main.java.viceCity.core.ControllerImpl;
import ViceCity.src.main.java.viceCity.core.EngineImpl;
import ViceCity.src.main.java.viceCity.core.interfaces.Controller;
import ViceCity.src.main.java.viceCity.core.interfaces.Engine;


public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl(); // TODO
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
