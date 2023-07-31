package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static vehicleShop.common.ConstantMessages.ADDED_WORKER;
import static vehicleShop.common.ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE;
import static vehicleShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private WorkerRepository workerRepository;
    private VehicleRepository vehicleRepository;
    private int countMadeVehicles;

    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
        this.countMadeVehicles = 0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        if (type.equals("FirstShift")) {
            worker = new FirstShift(workerName);
        } else if (type.equals("SecondShift")) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(WORKER_TYPE_DOESNT_EXIST);
        }
        workerRepository.add(worker);
        return String.format(ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        this.vehicleRepository.add(vehicle);
        return String.format(SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = workerRepository.findByName(workerName);
        if (worker == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Tool tool = new ToolImpl(power);
        worker.addTool(tool);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> availableWorkers = workerRepository.getWorkers().stream()
                .filter(worker -> worker.getStrength() > 70)
                .collect(Collectors.toList());
        if (availableWorkers.isEmpty()) {
            throw new IllegalArgumentException(NO_WORKER_READY);
        }
        Vehicle vehicle = vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();
        int brokenTools = 0;
        while (!availableWorkers.isEmpty() && !vehicle.reached()) {
            Worker worker = availableWorkers.get(0);
            shop.make(vehicle, worker);

            if (!worker.canWork() || worker.getTools().stream().allMatch(Tool::isUnfit)) {
                brokenTools += worker.getTools().stream().filter(Tool::isUnfit).count();
                availableWorkers.remove(worker);
                break;
            }
            brokenTools += worker.getTools().stream().filter(Tool::isUnfit).count();
        }
        if (vehicle.reached()) {
            countMadeVehicles++;
            return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, "done")
                    + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenTools);
        } else {
            return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, "not done")
                    + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenTools);
        }

    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!", countMadeVehicles)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        workerRepository.getWorkers().forEach(worker -> {
            sb.append(worker.toString()).append(System.lineSeparator());
        });

        return sb.toString().trim();
    }
}
