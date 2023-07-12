package PolymorphismExercises.Word;

public class Initialization {

    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface commandInterface = Initialization.buildCommandInterface(text);
        return buildCommandInterface(text);
    }
}
