package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {
    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);

        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {

        if (this.power -10 < 0) {
            this.power = 0;
        }
        setPower(getPower() - 10);
    }

    @Override
    public boolean isBroken() {
        return this.power == 0;
    }
}
