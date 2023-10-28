// package com.SunCycle.SunCycle.controller;

// import com.SunCycle.SunCycle.dto.SolarPanelRequestDTO;
// import com.SunCycle.SunCycle.dto.SolarPanelResponseDTO;
// import com.SunCycle.SunCycle.dto.Status;
// import com.SunCycle.SunCycle.service.SolarPanelService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContext;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.hamcrest.Matchers.is;

// @WebMvcTest(SolarPanelController.class)
// class SolarPanelControllerTest {
//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private SolarPanelService solarPanelService;

//     private SolarPanelRequestDTO requestDTO;
//     private SolarPanelResponseDTO responseDTO;


//     @BeforeEach
//     public void setup() {
//         requestDTO = new SolarPanelRequestDTO();

//         responseDTO = new SolarPanelResponseDTO();

//         Authentication authentication = mock(Authentication.class);
//         when(authentication.getName()).thenReturn("user"); // "user" should be a valid username

//         SecurityContext securityContext = mock(SecurityContext.class);
//         when(securityContext.getAuthentication()).thenReturn(authentication);
//         SecurityContextHolder.setContext(securityContext);
//     }

//     @Test
//     @WithMockUser(username = "admin") // this is an example, adjust it according to your needs
//     public void createPanel_Success() throws Exception {

//         String requestJson = "{"
//                 + "\"modelId\":\"1\","
//                 + "\"installationId\":\"4\","
//                 + "\"installationDate\":\"2023/10/14\","
//                 + "\"retirementDate\":\"2028/10/14\","
//                 + "\"recyclingMethod\":\"Recycle\""
//                 + "}";

//         responseDTO.setStatus(Status.SUCCESS);
//         when(solarPanelService.createPanel(any(SolarPanelRequestDTO.class))).thenReturn(responseDTO);

//         mockMvc.perform(MockMvcRequestBuilders.post("/panels/create")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(requestJson)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
//     }

//     @Test
//     @WithMockUser(username = "admin")
//     public void createPanel_Error() throws Exception {

//         String requestJson = "{"
//                 + "\"modelId\":\"1\","
//                 + "\"installationId\":\"4\","
//                 + "\"installationDate\":\"2023/10/14\","
//                 + "\"retirementDate\":\"2028/10/14\","
//                 + "\"recyclingMethod\":\"Recycle\""
//                 + "}";

//         responseDTO.setStatus(Status.ERROR);
//         responseDTO.setMessage("An error occurred");
//         when(solarPanelService.createPanel(any(SolarPanelRequestDTO.class))).thenReturn(responseDTO);

//         mockMvc.perform(MockMvcRequestBuilders.post("/panels/create")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(requestJson)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().is4xxClientError()) // This ensures that any 4xx error status matches
//                 .andExpect(content().string("An error occurred"));
//     }


//     @Test
//     @WithMockUser(username = "admin")
//     public void updatePanel_Success() throws Exception {
//         String requestJson = "{"
//                 + "\"modelId\":\"1\","
//                 + "\"installationId\":\"4\","
//                 + "\"installationDate\":\"2023/10/12\","
//                 + "\"retirementDate\":\"2028/10/12\","
//                 + "\"recyclingMethod\":\"Recycle twice\""
//                 + "}";

//         responseDTO.setStatus(Status.SUCCESS);
//         responseDTO.setMessage("Panel updated successfully");

//         when(solarPanelService.updatePanel(eq(1), any(SolarPanelRequestDTO.class))).thenReturn(responseDTO);

//         mockMvc.perform(MockMvcRequestBuilders.put("/panels/1/update")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(requestJson)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.status", is("SUCCESS")))
//                 .andExpect(jsonPath("$.message", is("Panel updated successfully")));
//     }

//     @Test
//     @WithMockUser(username = "admin")
//     public void updatePanel_Failure() throws Exception {
//         String requestJson = "{"
//                 + "\"modelId\":\"1\","
//                 + "\"installationId\":\"4\","
//                 + "\"installationDate\":\"2023/10/12\","
//                 + "\"retirementDate\":\"2028/10/12\","
//                 + "\"recyclingMethod\":\"Recycle twice\""
//                 + "}";


//         responseDTO.setStatus(Status.NOT_FOUND);
//         responseDTO.setMessage("Panel update failed due to panel not found");

//         when(solarPanelService.updatePanel(eq(1), any(SolarPanelRequestDTO.class))).thenReturn(responseDTO);

//         mockMvc.perform(MockMvcRequestBuilders.put("/panels/1/update")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(requestJson)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().is4xxClientError()) // This ensures that any 4xx error status matches
//                 .andExpect(content().string("Panel update failed due to panel not found"));
//     }


//     @Test
//     @WithMockUser(username = "admin")
//     public void deletePanel_WhenPanelExists_ShouldReturnOk() throws Exception {
//         // Arrange

//         responseDTO.setStatus(Status.SUCCESS);
//         when(solarPanelService.deletePanel(anyInt())).thenReturn(responseDTO);

//         // Act & Assert
//         mockMvc.perform(delete("/panels/{panelId}/delete", 1)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().isOk())
//                 .andExpect(content().json("{\"status\":\"SUCCESS\"}"));
//     }

//     @Test
//     @WithMockUser(username = "admin")
//     public void deletePanel_WhenPanelNotFound_ShouldReturnBadRequest() throws Exception {
//         // Arrange

//         responseDTO.setStatus(Status.NOT_FOUND);
//         responseDTO.setMessage("Panel not found");
//         when(solarPanelService.deletePanel(anyInt())).thenReturn(responseDTO);

//         // Act & Assert
//         mockMvc.perform(delete("/panels/{panelId}/delete", 1)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(content().string("Panel not found"));
//     }

//     @Test
//     @WithMockUser(username = "admin")
//     public void deletePanel_WhenError_ShouldReturnBadRequest() throws Exception {
//         // Arrange

//         responseDTO.setStatus(Status.ERROR);
//         responseDTO.setMessage("Internal error");
//         when(solarPanelService.deletePanel(anyInt())).thenReturn(responseDTO);

//         // Act & Assert
//         mockMvc.perform(delete("/panels/{panelId}/delete", 1)
//                         .with(SecurityMockMvcRequestPostProcessors.csrf()))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(content().string("Internal error"));
//     }

// }