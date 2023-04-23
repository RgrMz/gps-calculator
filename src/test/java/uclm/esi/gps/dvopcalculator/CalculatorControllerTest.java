package uclm.esi.gps.dvopcalculator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class CalculatorControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
    @Test
    @Order(1)
    void testAdditionPositiveNumbers() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "2").param("n2", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }
    @Test
    @Order(2)
    void testAdditionNegativeNumbers() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "-2").param("n2", "-3"))
                .andExpect(status().isOk())
                .andExpect(content().string("-5.0"));
    }
    @Test
    @Order(3)
    void testAdditionPosNegNumbers() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "5").param("n2", "-2"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.0"));
    }
    @Test
    @Order(4)
    void testAdditionFloatNumbers() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "2.5").param("n2", "3.7"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.2"));
    }
    @Test
    @Order(5)
    void testAdditionZero() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "10").param("n2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("10.0"));
    }
    @Test
    @Order(6)
    void testAdditionBadRequests() throws Exception
    {
    	// Text characters
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "a").param("n2", "3"))
        		.andExpect(status().isBadRequest());
        
        // Special characters
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "#").param("n2", "3"))
        		.andExpect(status().isBadRequest());
        
        // Number too large
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "1000000000000").param("n2", "30"))
        		.andExpect(status().isBadRequest());
        
        // Number too small
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .param("n1", "0.000000000001").param("n2", "30"))
        		.andExpect(status().isBadRequest());
    }
    
}
