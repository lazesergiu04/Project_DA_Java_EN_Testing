package com.openclassrooms.lambazon.store.controller;

import com.openclassrooms.lambazon.store.service.OrderService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



@WebMvcTest(controllers={OrderController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    ProductController productController;

    @Test
    public void givenACart_whenAnOrderIsCreated_thenItShouldPresentAnOrderForm() throws Exception {
        MvcResult response = mockMvc.perform(get("/order"))
            .andExpect(view().name("order"))
            .andExpect(status().is2xxSuccessful())
            .andReturn();
        assertThat(response.getResponse().getContentAsString(), containsString("Please checkout"));
    }

    @Test
    @Ignore("FIXME - It looks like this should be a redirect")
    // It looks like this should be a redirect. Eg. 3xx
    public void givenAnExistingCart_whenAnItemIsRemovedWithCsrf_thenItShouldNoLongerBeInTheCart() throws Exception {
        MvcResult response = mockMvc.perform(post("/order/removeFromCart")
            .param("productId", "1234")
            .with(csrf()))
            .andExpect(status().is2xxSuccessful())
            .andReturn();

        // ensure that we collaborated with the orderService to remove it
        verify(orderService, times(1)).removeFromCart(any(Long.class));
    }

    @Test
    public void givenAnExistingCart_whenAnItemIsRemovedWithoutCsrf_thenItShouldRemainInTheCart() throws Exception {
        MvcResult response = mockMvc.perform(post("/order/removeFromCart")
            .param("productId", "1234"))
            .andExpect(status().is4xxClientError())
            .andReturn();

        // ensure that we collaborated with the orderService to remove it
        verify(orderService, never()).removeFromCart(any(Long.class));
    }

}

