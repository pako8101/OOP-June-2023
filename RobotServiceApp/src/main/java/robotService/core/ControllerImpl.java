package robotService.core;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepository supplements;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
//        Service service;
//        switch (type) {
//            case "SecondaryService":
//                service = new SecondaryService(name);
//                break;
//            case "MainService":
//                service = new MainService(name);
//                break;
//            default:
//                throw new NullPointerException(INVALID_SERVICE_TYPE);
//        }
//
//        services.put(name, service);
//
//        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
//
//    }
        Service service = type.equals("MainService")
                ? new MainService(name)
                : new SecondaryService(name);
        Service prevService = this.services.get(type);
        if (prevService == null) {
            this.services.put(name, service);
            return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
        }
        throw new NullPointerException(INVALID_SERVICE_TYPE);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement = type.equals("PlasticArmor")
                ? new PlasticArmor()
                : new MetalArmor();
        Supplement prevSupplement = this.supplements.findFirst(type);
        if (prevSupplement == null) {
            this.supplements.addSupplement(supplement);
            return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
        }
        throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = this.supplements.findFirst(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        services.get(serviceName).addSupplement(supplement);
        this.supplements.removeSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        if (robotType.equals("MaleRobot")) {
            robot = new MaleRobot(robotName, robotKind, price);
        } else if (robotType.equals("FemaleRobot")) {
            robot = new FemaleRobot(robotName, robotKind, price);
        } else {
            throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }
        Service service = services.get(serviceName);
        String serviceType = service.getClass().getSimpleName();
        boolean serviceForMaleRobot = serviceType.equals("MainService")
                && robotType.equals("MaleRobot");
        boolean serviceForFemaleRobot = serviceType.equals("SecondaryService")
                && robotType.equals("FemaleRobot");
        if (serviceForMaleRobot || serviceForFemaleRobot) {
            service.addRobot(robot);
        } else {
            return UNSUITABLE_SERVICE;
        }
        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = this.services.get(serviceName);
        service.feeding();
        return String.format(FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
       Service service = services.get(serviceName);
        double robotPrice = service.getRobots().stream().
                mapToDouble(Robot::getPrice)
                .sum();
        double supplementsPrice = service.getSupplements()
                .stream().mapToDouble(Supplement::getPrice).sum();
        return String.format(VALUE_SERVICE, serviceName, robotPrice+supplementsPrice);
    }

    @Override
    public String getStatistics() {
        return services.values().stream()
                .map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
