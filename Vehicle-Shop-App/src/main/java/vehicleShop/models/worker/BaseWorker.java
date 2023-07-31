package vehicleShop.models.worker;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;

public class BaseWorker implements Worker {
    private String name;
    private int strength;
    private Collection<Tool> tools;

    public BaseWorker(String name, int strength) {
        this.setName(name);
        this.setStrength(strength);
        this.tools = new ArrayList<>();
    }

    public void setName(String name) {
if (name==null|| name.equals("")){
    throw  new IllegalArgumentException(ExceptionMessages.WORKER_NAME_NULL_OR_EMPTY);
}
        this.name = name;
    }

    public void setStrength(int strength) {
        if (strength<0){
            throw  new IllegalArgumentException(ExceptionMessages.WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    public void setTools(Collection<Tool> tools) {
        this.tools = tools;
    }

    @Override
    public void working() {
        this.strength -= 10;
        if (this.strength < 0) {
            this.strength = 0;
        }
    }

    @Override
    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    @Override
    public boolean canWork() {
        return this.getStrength() > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public Collection<Tool> getTools() {
        return this.tools;
    }

    @Override
    public String toString() {
        long countFitTools = this.getTools().stream().filter(t->t.getPower()>0).count();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: " + name + ", Strength: " + strength)).append(System.lineSeparator());
        sb.append(String.format("Tools: %d fit left", countFitTools)).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
