package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.repository.Dao.OwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;
    private OwnerService ownerService;
    private Owner testOwner;

    @BeforeEach
    public void init() {
        ownerService = new OwnerService(ownerRepository);
        testOwner = Owner.builder().name("Test owner").build();
    }

    @Test
    public void getAllTest() {
        List<Owner> expectedOwners = List.of(
                Owner.builder().name("Owner 1").build(),
                Owner.builder().name("Owner 2").build(),
                Owner.builder().name("Owner 3").build()
        );

        Mockito.when(ownerRepository.getAll()).thenReturn(expectedOwners);

        List<Owner> owners = ownerService.getAll();
        Assertions.assertEquals(owners, expectedOwners);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(ownerRepository.findById(anyInt())).thenReturn(testOwner);
        Owner owner = ownerService.findById(anyInt());
        Assertions.assertEquals(owner, testOwner);
    }

    @Test
    public void addNewTest() {
        doNothing().when(ownerRepository).save(testOwner);
        ownerService.addNew(testOwner);
        verify(ownerRepository, times(1)).save(testOwner);
    }

    @Test
    public void updateOwnerTest() {
        doNothing().when(ownerRepository).update(testOwner);
        ownerService.update(testOwner);
        verify(ownerRepository, times(1)).update(testOwner);
    }

    @Test
    public void deleteOwnerTest() {
        doNothing().when(ownerRepository).delete(anyInt());
        ownerService.delete(anyInt());
        verify(ownerRepository, times(1)).delete(anyInt());
    }

}
