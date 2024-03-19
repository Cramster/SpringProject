
import org.example.Entity.Seller;
import org.example.Exception.SellerException;
import org.example.Repository.SellerRepository;
import org.example.Service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private SellerService sellerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveSeller_ValidSeller_Success() throws SellerException {
        // Arrange
        Seller seller = new Seller(1, "John Doe", new ArrayList<>());

        // Mock repository behavior
        when(sellerRepository.findAll()).thenReturn(new ArrayList<>());
        when(sellerRepository.save(seller)).thenReturn(seller);

        // Act
        Seller savedSeller = sellerService.saveSeller(seller);

        // Assert
        assertNotNull(savedSeller);
        assertEquals(seller.getSellerId(), savedSeller.getSellerId());
        assertEquals(seller.getSellerName(), savedSeller.getSellerName());
        verify(sellerRepository, times(1)).save(seller);
    }

    @Test
    void testSaveSeller_DuplicateName_ThrowsException() {
        // Arrange
        Seller seller = new Seller(2, "John Doe", new ArrayList<>());

        List<Seller> existingSellers = new ArrayList<>();
        existingSellers.add(new Seller(1, "John Doe", new ArrayList<>()));

        // Mock repository behavior
        when(sellerRepository.findAll()).thenReturn(existingSellers);

        // Act and Assert
        assertThrows(SellerException.class, () -> sellerService.saveSeller(seller));
        verify(sellerRepository, never()).save(any());
    }


}