package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.HELPER_DOESNT_EXIST;
import static fairyShop.common.ExceptionMessages.NO_HELPER_READY;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository = new HelperRepository();
    private PresentRepository presentRepository = new PresentRepository();
    private static int countBrokenInstruments;

    public ControllerImpl() {

    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper = type.equals("Happy")
                ? new Happy(helperName)
                : new Sleepy(helperName);
        Helper prevHelper = this.helperRepository.findByName(helperName);
        if (prevHelper == null) {
            this.helperRepository.add(helper);
            return String.format(ADDED_HELPER, type, helperName);
        }

        throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);
        Helper helper = this.helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        helper.addInstrument(instrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpersProper = this.helperRepository.getModels().stream()
                .filter(helper -> helper.getEnergy() > 50)
                .collect(Collectors.toList());
        if (helpersProper.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        Present presentToMake = this.presentRepository.findByName(presentName);
        Shop shop = new ShopImpl();
        for (Helper helper : helpersProper) {
            shop.craft(presentToMake, helper);
            countBrokenInstruments+=(int)helper.getInstruments().stream().filter(Instrument::isBroken).count();
        if (presentToMake.isDone()){
            break;
        }
        }

       return String.format(PRESENT_DONE,
               presentToMake.getName(), presentToMake.isDone() ? "done" : "not done")+
     String.format(COUNT_BROKEN_INSTRUMENTS,countBrokenInstruments);
    }

    @Override
    public String report() {
        int size = (int) presentRepository.getModels().stream()
                .filter(Present::isDone).count();
        List<String> collect = helperRepository.getModels()
                .stream().map(helper -> String.format("Name: %s%n" +
                                "Energy: %d%n" +
                                "Instruments: %d not broken left%n",
                        helper.getName(), helper.getEnergy(),
                        (int) helper.getInstruments().stream()
                                .filter(instrument -> !instrument.isBroken())
                                .count())).collect(Collectors.toList());
        return String.format("%d presents are done!%n", size)
                + String.format("Helpers info:%n") + String.join("", collect).trim();
//        StringBuilder report = new StringBuilder();
//        report.append(String.format("%d presents are done!\n", countBrokenInstruments));
//        report.append("Helpers info:\n");
//        Collection<Helper>helpersCanWork = helperRepository.getModels().stream().filter(Helper::canWork)
//                .collect(Collectors.toList());
//        for (Helper helper : helpersCanWork) {
//            report.append("Name: ").append(helper.getName()).append("\n");
//            report.append("Energy: ").append(helper.getEnergy()).append("\n");
//            report.append("Instruments: ").append(helper.getInstruments().size()).append(" not broken left\n");
//            // You can add more information about the helper if needed
//        }
//
//        return report.toString();
    }
    }

