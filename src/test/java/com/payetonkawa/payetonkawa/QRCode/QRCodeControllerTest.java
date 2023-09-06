package com.payetonkawa.payetonkawa.QRCode;

import com.payetonkawa.payetonkawa.customers.Customers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

class QRCodeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IQRCodeService qrCodeService;

    @InjectMocks
    private QRCodeController qrCodeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(qrCodeController).build();
    }

    @Test
    void getQRCode() throws Exception {
        String email = "baptiste@gmail.com";
        Model model = mock(Model.class);

        when(qrCodeService.getQRCode(email, model)).thenReturn("viewName");

        mockMvc.perform(get("/api/v1/qrCode").param("email", email))
                .andExpect(status().isOk());
    }
}
