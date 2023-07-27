package Command;

public class CommandPattern {
    // Receiver
    class Light {
        public void turnOn() {
            System.out.println("Light is on");
        }

        public void turnOff() {
            System.out.println("Light is off");
        }
    }

    // Command interface
    interface Command {
        void execute();
    }

    // Concrete command implementations
    class TurnOnCommand implements Command {
        private Light light;

        public TurnOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    class TurnOffCommand implements Command {
        private Light light;

        public TurnOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    // Invoker
    class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            command.execute();
        }
    }

}
