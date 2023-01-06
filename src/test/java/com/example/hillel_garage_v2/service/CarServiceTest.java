package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.repository.Dao.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    private CarService carService;

    @BeforeEach
    public void init() {
        carService = new CarService(carRepository);
    }

    @Test
    public void getAllByOwnerId() {

        List<Car> expectedCars = List.of(
                Car.builder().model("Car 1").build(),
                Car.builder().model("Car 2").build(),
                Car.builder().model("Car 3").build()
        );
        Mockito.when(carRepository.getAllByOwnerID(anyInt())).thenReturn(expectedCars);
        List<Car> cars = carService.getAllByOwnerID(anyInt());
        Assertions.assertEquals(cars, expectedCars);
    }

    @Test
    public void findByIdTest() {

        Car expectedCar = Car.builder().model("Test car").build();
        Mockito.when(carRepository.findById(anyInt())).thenReturn(expectedCar);
        Car car = carService.findById(anyInt());
        Assertions.assertEquals(car, expectedCar);
    }

    @Test
    public void addNewTest() {
        Car testCar = Car.builder().model("Test car").build();
        doNothing().when(carRepository).save(testCar);
        carService.addNew(testCar);
        verify(carRepository, times(1)).save(testCar);
    }

    @Test
    public void updateCarTest() {
        Car testCar = Car.builder().model("Test car").build();
        doNothing().when(carRepository).update(testCar);
        carService.update(testCar);
        verify(carRepository, times(1)).update(testCar);
    }

    @Test
    public void deleteCarTest() {
        doNothing().when(carRepository).delete(anyInt());
        carService.delete(anyInt());
        verify(carRepository, times(1)).delete(anyInt());
    }
}




