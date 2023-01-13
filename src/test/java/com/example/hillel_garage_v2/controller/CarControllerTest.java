package com.example.hillel_garage_v2.controller;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.service.CarService;
import com.example.hillel_garage_v2.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private OwnerService ownerService;

    @Test
    public void shouldReturnPageWithListOfOwnerCars() throws Exception {

        when(ownerService.findById(anyInt())).thenReturn(Owner.builder().build());

        mockMvc.perform(get("/cars/{owner_id}", anyInt()))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/list"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    public void shouldReturnRegistrationForm() throws Exception {

        when(ownerService.findById(anyInt())).thenReturn(Owner.builder().build());

        mockMvc.perform(get("/cars/{owner_id}/registration", anyInt()))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    public void savingCarShouldRedirectToMainPage() throws Exception {

        when(carService.save(any())).thenReturn(Car.builder().build());

        mockMvc.perform(post("/cars")
                        .flashAttr("car", Car.builder().build())
                )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldReturnUpdatingForm() throws Exception {

        when(carService.findById(anyInt())).thenReturn(Car.builder().build());
        when(ownerService.findById(anyInt())).thenReturn(Owner.builder().build());

        mockMvc.perform(get("/cars/{id}/updating", anyInt()))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/update"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    public void deletingShouldRedirectToMainPage() throws Exception {

        when(carService.findById(anyInt())).thenReturn(Car.builder().build());
        doNothing().when(carService).deleteById(anyInt());

        mockMvc.perform(delete("/cars/{id}", anyInt()))
                .andExpect(status().is3xxRedirection());
    }
}
