package vehicleShop.models.tool;

import vehicleShop.common.ExceptionMessages;

import static vehicleShop.common.ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO;

public class ToolImpl implements Tool {
    private int power;

    public ToolImpl(int power) {
        this.power = power;
    }

    private void setPower(int power) {
        if (power<0){
            throw  new IllegalArgumentException(TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void decreasesPower() {
//        int currentPower = getPower();
//        int decreasePower = currentPower - 5;
//        if (decreasePower < 0) {
//            decreasePower = 0;
//        }
//        this.setPower(decreasePower);
        setPower(Math.max(getPower()-5,0));
    }

    @Override
    public boolean isUnfit() {
        return this.getPower()==0;
    }
}
