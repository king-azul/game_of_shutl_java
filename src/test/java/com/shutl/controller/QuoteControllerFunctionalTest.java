package com.shutl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shutl.Application;
import com.shutl.model.Quote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QuoteControllerFunctionalTest {

    @Autowired private WebApplicationContext webApplicationContext;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testBasicService() throws Exception {
        Quote quoteData = new Quote("SW1A1AA", "EC2A3LT");
        MvcResult result = this.mockMvc.perform(post("/quote")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

        Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
        assertEquals("SW1A1AA", quote.getPickupPostcode());
        assertEquals("EC2A3LT", quote.getDeliveryPostcode());
        assertEquals(new Long(316), quote.getPrice());
    }

    @Test
    public void testVariablePricingByDistance() throws Exception {
        Quote quoteData = new Quote("AL15WD", "EC2A3LT");
        MvcResult result = this.mockMvc.perform(post("/quote")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

        Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
        assertEquals("AL15WD", quote.getPickupPostcode());
        assertEquals("EC2A3LT", quote.getDeliveryPostcode());
        assertEquals(new Long(305), quote.getPrice());
    }

        @Test
        public void testBicycleVehiclePricing() throws Exception {
            Quote quoteData = new Quote("AL15WD", "EC2A3LT", "bicycle");
            MvcResult result = this.mockMvc.perform(post("/quote")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
                .andExpect(status().isOk())
                .andReturn();

            Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
            assertEquals("AL15WD", quote.getPickupPostcode());
            assertEquals("EC2A3LT", quote.getDeliveryPostcode());
            assertEquals("bicycle", quote.getVehicle());
            assertEquals(new Long((long) (305 * 1.1)), quote.getPrice());
        }

        @Test
        public void testMotorbikeVehiclePricing() throws Exception {
            Quote quoteData = new Quote("AL15WD", "EC2A3LT", "motorbike");
            MvcResult result = this.mockMvc.perform(post("/quote")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
                .andExpect(status().isOk())
                .andReturn();

            Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
            assertEquals("AL15WD", quote.getPickupPostcode());
            assertEquals("EC2A3LT", quote.getDeliveryPostcode());
            assertEquals("motorbike", quote.getVehicle());
            assertEquals(new Long((long) (305 * 1.15)), quote.getPrice());
        }

        @Test
        public void testCarVehiclePricing() throws Exception {
            Quote quoteData = new Quote("AL15WD", "EC2A3LT", "parcel_car");
            MvcResult result = this.mockMvc.perform(post("/quote")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
                .andExpect(status().isOk())
                .andReturn();

            Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
            assertEquals("AL15WD", quote.getPickupPostcode());
            assertEquals("EC2A3LT", quote.getDeliveryPostcode());
            assertEquals("parcel_car", quote.getVehicle());
            assertEquals(new Long((long) (305 * 1.2)), quote.getPrice());
        }

        @Test
        public void testSmallVanVehiclePricing() throws Exception {
            Quote quoteData = new Quote("AL15WD", "EC2A3LT", "small_van");
            MvcResult result = this.mockMvc.perform(post("/quote")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
                .andExpect(status().isOk())
                .andReturn();

            Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
            assertEquals("AL15WD", quote.getPickupPostcode());
            assertEquals("EC2A3LT", quote.getDeliveryPostcode());
            assertEquals("small_van", quote.getVehicle());
            assertEquals(new Long((long) (305 * 1.3)), quote.getPrice());
        }

        @Test
        public void testLargeVanVehiclePricing() throws Exception {
            Quote quoteData = new Quote("AL15WD", "EC2A3LT", "large_van");
            MvcResult result = this.mockMvc.perform(post("/quote")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
                .andExpect(status().isOk())
                .andReturn();

            Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
            assertEquals("AL15WD", quote.getPickupPostcode());
            assertEquals("EC2A3LT", quote.getDeliveryPostcode());
            assertEquals("large_van", quote.getVehicle());
            assertEquals(new Long((long) (305 * 1.4)), quote.getPrice());
        }

        @Test
        public void testVehiclePriceLimit() throws Exception {
            Quote quoteData = new Quote("AL15WD", "EC2A3LT", "motorbike");
            MvcResult result = this.mockMvc.perform(post("/quote")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
                .andExpect(status().isOk())
                .andReturn();

            Quote quote = objectMapper.readValue(result.getResponse().getContentAsString(), Quote.class);
            assertEquals("AL15WD", quote.getPickupPostcode());
            assertEquals("EC2A3LT", quote.getDeliveryPostcode());
            assertEquals("motorbike", quote.getVehicle());
            quote.setPrice(new Long(1200));
            assertEquals("small_van", quote.getVehicle());
            System.out.println(1200/1.15 * 1.3);
            System.out.println(quote.getPrice());
        }

}
