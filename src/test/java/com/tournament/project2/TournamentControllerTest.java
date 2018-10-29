package com.tournament.project2;


import com.tournament.project2.controller.TournamentController;
import com.tournament.project2.service.TournamentService;
import com.tournament.project2.tournament.model.Match;
import com.tournament.project2.tournament.model.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@RunWith(SpringRunner.class)

@WebMvcTest(value = TournamentController.class, secure = false)
public class TournamentControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    private List<Match> mockSchedule()
    {
        Tournament tournament = new Tournament();
        // set tournament details
        // with startDate, maxMatch and gap between matches
        // and team
        //
        //
        return tournament.getScheduleMatches();
    }

    @Test
    public void getSchedule() throws Exception
    {
        Mockito.when(
                tournamentService.retrieveTournamentSchedule()
        ).thenReturn(mockSchedule());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/tournament/schedule").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{ expected JSON string will come here based }";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
        
    }
}
