/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Harri
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UutisControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/etusivu"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void maailmaStatusOk() throws Exception {
        mockMvc.perform(get("/maailma"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void urheiluStatusOk() throws Exception {
        mockMvc.perform(get("/urheilu"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void kotimaaStatusOk() throws Exception {
        mockMvc.perform(get("/kotimaa"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void sääStatusOk() throws Exception {
        mockMvc.perform(get("/sää"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void politiikkaStatusOk() throws Exception {
        mockMvc.perform(get("/politiikka"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void luetuimmatStatusOk() throws Exception {
        mockMvc.perform(get("/luetuimmat"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }

    @Test
    public void uusimmatStatusOk() throws Exception {
        mockMvc.perform(get("/uusimmat"))
                .andExpect(model().attributeExists("uutiset"))
                .andExpect(status().isOk());
    }



}
