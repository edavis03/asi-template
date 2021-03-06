package mil.army.futures.asitemplate.controllers;


import mil.army.futures.asitemplate.Team;
import mil.army.futures.asitemplate.services.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
class TeamControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    void shouldSaveANewTeamWhenANewTeamIsCreated() throws Exception {
        when(teamService.createTeam(new Team("first-team-name"))).thenReturn(new Team(1L, "first-team-name"));

        this.mockMvc.perform(post("/teams").contentType(MediaType.TEXT_PLAIN).content("first-team-name"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("first-team-name")));

        verify(teamService).createTeam(new Team("first-team-name"));
    }

    @Test
    void shouldRetrieveAllTeamsWhenGettingTeams() throws Exception {
        when(teamService.getTeams()).thenReturn(List.of(new Team(1L, "first-team-name"), new Team(2L, "second-team-name")));

        this.mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("first-team-name")))
                .andExpect(content().string(containsString("second-team-name")));

        verify(teamService).getTeams();
    }
}