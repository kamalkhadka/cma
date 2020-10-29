package com.amerisave.cma.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.amerisave.cma.model.Contact;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactResource.class)
public class ContactResourceTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private ContactResource resource;
    
    
    @Test
    public void givenContact_whenGetContacts_thenReturnJsonArray()
      throws Exception {
        
        Contact kamal = new Contact();
        kamal.setFirstName("Kamal");
        kamal.setLastName("Khadka");
        kamal.setEmail("kml.kdk@gmail.com");
     
        List<Contact> allContacts = Arrays.asList(kamal);
     
        given(resource.getAllContacts()).willReturn(allContacts);
     
        mvc.perform(get("/contacts")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].firstName", is(kamal.getFirstName())));
    }

	
}
