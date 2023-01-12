package com.example.hillel_garage_v2.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @Test
    public void shouldReturnTittlePageWithListOfOwners() throws Exception {

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/list"))
                .andExpect(model().attributeExists("owners"));
    }

    @Test
    public void shouldReturnRegistrationForm() throws Exception {

        mockMvc.perform(get("/owners/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/new"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    public void savingOwnerShouldRedirectToMainPage() throws Exception {

        when(ownerService.save(any())).thenReturn(Owner.builder().build());

        mockMvc.perform(post("/owners")
                        .flashAttr("owner", Owner.builder().build())
                )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldReturnUpdatingForm() throws Exception {

        when(ownerService.findById(anyInt())).thenReturn(Owner.builder().build());

        mockMvc.perform(get("/owners/{id}/updating", anyInt()))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/update"))
                .andExpect(model().attributeExists("owner"));
    }


    @Test
    public void deletingShouldRedirectToMainPage() throws Exception {

        doNothing().when(ownerService).deleteById(anyInt());

        mockMvc.perform(delete("/owners/{id}", anyInt()))
                .andExpect(status().is3xxRedirection());
    }
}
