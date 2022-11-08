package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.CreateFighterUseCase;
import com.boxvent.boxventwebsite.business.GetFighterUseCase;
import com.boxvent.boxventwebsite.business.GetFightersUseCase;
import com.boxvent.boxventwebsite.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
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
    private GetFighterUseCase getFighterUseCase;

    @Test
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
        verifyNoInteractions(getFighterUseCase);
    }

    @Test
    void createFighter_shouldCreateAndReturn201_WhenRequestIsValid() throws
            Exception {
        CreateFighterRequest expectedFighter = CreateFighterRequest.builder()
                .name("Vlad the boxer")
                .wins(10L)
                .draws(1L)
                .loses(5L)
                .build();
        when(createFighterUseCaseMock.createFighter(expectedFighter))
                .thenReturn(CreateFighterResponse.builder().fighterId(1337L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/fighters")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "name" : "Vlad the boxer",
                            "wins":10,
                            "draws":1,
                            "loses":2
                            
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
    void createFighter_shouldNotCreateAndReturn400_WhenMissingFields() throws
            Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/fighters")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "name" : "",
                            "wins":"",
                            "draws":"",
                            "loses":""
                        }"""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
[
{"field": "name","error": "must not be blank"},
{"field": "wins","error": "must not be blank"},
{"field": "draws","error": "must not be blank"},
{"field": "loses","error": "must not be blank"},
]
"""));
        verifyNoInteractions(createFighterUseCaseMock);

    }

    @Test
    void getFighter() {
    }

}