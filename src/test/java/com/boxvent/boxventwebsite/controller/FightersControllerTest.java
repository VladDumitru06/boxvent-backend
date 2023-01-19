package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.business.exception.FighterNameAlreadyExistsException;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.configuration.security.WebSecurityConfig;
import com.boxvent.boxventwebsite.domain.*;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.NoInteractions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RunAs;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FightersController.class)
class FightersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetFightersUseCase getFightersUseCaseMock;
    @MockBean
    private CreateFighterUseCase createFighterUseCaseMock;
    @MockBean
    private UpdateFighterUseCase updateFighterUseCaseMock;
    @MockBean
    private GetFighterUseCase getFighterUseCaseMock;

    @MockBean
    private DeleteFighterUseCase deleteFighterUseCaseMock;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;


    @Test
    @WithMockUser(username = "Vlad321", roles = {"CLIENT"})
    void getAllFighters_shouldReturn200ResponseWithFightersArray() throws
            Exception {
        BoxingRecord vladRecord = BoxingRecord.builder().wins(10L).draws(0L).loses(2L).build();
        BoxingRecord dejiRecord = BoxingRecord.builder().wins(15L).draws(1L).loses(0L).build();
        GetAllFightersResponse response = GetAllFightersResponse.builder()
                .fighters(List.of(
                        Fighter.builder().id(1L).name("Vlad the boxer").boxingRecord(vladRecord).build(),
                        Fighter.builder().id(2L).name("Deji").boxingRecord(dejiRecord).build()
                )).build();
        when(getFightersUseCaseMock.getFighters())
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/fighters"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(content().json
                        ("""
                                {"fighters" : [{
                                                "id" : 1,
                                                "name" : "Vlad the boxer",
                                                "boxingRecord" : {"wins": 10,"draws": 0,"loses" : 2}
                                },{
                                                "id" : 2,
                                                "name" : "Deji",
                                                "boxingRecord" : {"wins": 15,"draws": 1,"loses" : 0}
                                }]}    
                                """));
        verify(getFightersUseCaseMock).getFighters();
        verifyNoInteractions(createFighterUseCaseMock);
        verifyNoInteractions(getFighterUseCaseMock);
    }
    @Test
    @WithMockUser(username = "Vlad321", roles = {"CLIENT"})
    void getFighter_shouldReturn200ResponseWithFighter() throws
            Exception {
        BoxingRecord vladRecord = BoxingRecord.builder().wins(10L).draws(0L).loses(2L).build();
        Fighter vladBoxer = Fighter.builder().id(1L).name("Vlad").boxingRecord(vladRecord).build();
        ResponseEntity<Fighter> response = new ResponseEntity<>(vladBoxer, HttpStatus.OK);
        when(getFighterUseCaseMock.getFighter(1L))
                .thenReturn(response.getBody());

        mockMvc.perform(MockMvcRequestBuilders.get("/fighters/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(content().json
                        ("""
                                {
                                    "id": 1,
                                    "name": "Vlad",
                                    "boxingRecord": {
                                        "wins": 10,
                                        "draws": 0,
                                        "loses": 2
                                    }
                                }   
                                """));
        verify(getFighterUseCaseMock).getFighter(1L);
        verifyNoInteractions(createFighterUseCaseMock);
    }
    @Test
    @WithMockUser(username = "Vlad321", roles = {"CLIENT"})
    void getFighter_shouldReturn400Response() throws
            Exception {

        when(getFighterUseCaseMock.getFighter(1L))
                .thenThrow(new InvalidFighterException());

        mockMvc.perform(MockMvcRequestBuilders.get("/fighters/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type",
                        "text/plain;charset=UTF-8"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidFighterException));
        verify(getFighterUseCaseMock).getFighter(1L);
        verifyNoInteractions(createFighterUseCaseMock);
    }
    @Test
    @WithMockUser(username = "Vlad321", roles = {"ADMIN"})
    void createFighter_shouldCreateAndReturn201_WhenRequestIsValid() throws
            Exception {
        CreateFighterRequest expectedFighter = CreateFighterRequest.builder()
                .name("Vlad the boxer")
                .image("https://i.imgur.com/1j2j3j4.jpg")
                .wins(10L)
                .draws(1L)
                .loses(5L)
                .build();
        when(createFighterUseCaseMock.createFighter(expectedFighter))
                .thenReturn(CreateFighterResponse.builder().fighterId(1337L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/fighters")
                        .contentType(APPLICATION_JSON_VALUE)
                        .with(csrf())
                        .content("""
                                {
                                "name":"Vlad the boxer",
                                "wins":10,
                                "draws":1,
                                "loses":5,
                                "image":"https://i.imgur.com/1j2j3j4.jpg"  
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {"fighterId": 1337}
                        """));

        verify(createFighterUseCaseMock).createFighter(expectedFighter);
    }

    @Test
    @WithMockUser(username = "Vlad321", roles = {"CLIENT"})
    void createFighter_shouldNotCreateAndReturn400_WhenMissingFields() throws
            Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/fighters")
                        .contentType(APPLICATION_JSON_VALUE)
                        .with(csrf())
                        .content("""
                                {
                                    "name":"",
                                    "wins":"",
                                    "draws":"",
                                    "loses":"",
                                    "image":""
                                    
                                }"""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [
                        {"field": "name","error": "length must be between 2 and 2147483647"},
                        {"field": "wins","error": "must not be null"},
                        {"field": "draws","error": "must not be null"},
                        {"field": "loses","error": "must not be null"},
                        {"field": "image","error": "must not be blank"}
                        ]
                        """));
        verifyNoInteractions(createFighterUseCaseMock);

    }


}