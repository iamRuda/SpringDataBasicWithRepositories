package com.example.springdatabasicdemo;

import com.example.springdatabasicdemo.models.Bike;
import com.example.springdatabasicdemo.models.Car;
import com.example.springdatabasicdemo.models.Plane;
import com.example.springdatabasicdemo.models.Truck;
import com.example.springdatabasicdemo.repositories.BikeRepository;
import com.example.springdatabasicdemo.repositories.CarRepository;
import com.example.springdatabasicdemo.repositories.PlaneRepository;
import com.example.springdatabasicdemo.repositories.TruckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BikeRepository bikeRepository;
    private final CarRepository carRepository;
    private final PlaneRepository planeRepository;
    private final TruckRepository truckRepository;

    public CommandLineRunnerImpl(BikeRepository bikeRepository, CarRepository carRepository, PlaneRepository planeRepository, TruckRepository truckRepository) {
        this.bikeRepository = bikeRepository;
        this.carRepository = carRepository;
        this.planeRepository = planeRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();


        updateBikeFuelType(1, "Kerosin");
        deleteBikeById(2);
        printAllBike();
    }

    private void printAllBike() {bikeRepository.findAll().forEach(System.out::println);}

    private void updateBikeFuelType (int idBike, String fuelType){
        Bike bike = bikeRepository.findById(idBike);
        bike.setFuelType(new String(fuelType));
        bikeRepository.save(bike);
    }

    private void deleteBikeById(int idBike){
        Bike bike = bikeRepository.findById(idBike);
        bikeRepository.delete(bike);
    }



    private void seedData() throws IOException {
        // Добавление в БД записей
        Bike bike = new Bike("R1", BigDecimal.valueOf(300.49), "Oil");
        Bike another_bike = new Bike("H1", BigDecimal.valueOf(530.29), "Electro");
        Car car = new Car("GT300", BigDecimal.valueOf(5300.01), "Petrol-100", 4);
        Plane plane = new Plane("AirSoft", BigDecimal.valueOf(50000.34), "Kerosin", "S9", 12);
        Truck truck = new Truck("Kamaz", BigDecimal.valueOf(3145.12), "Disel", 4.25);

        bikeRepository.save(bike);
        bikeRepository.save(another_bike);
        carRepository.save(car);
        planeRepository.save(plane);
        truckRepository.save(truck);
    }
}
