package mil.army.futures.asitemplate.controllers

import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.services.TeamService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
class TeamController(private val teamService: TeamService) {

    @GetMapping("/teams")
    fun getTeams(): List<String> {
        val teamNames = ArrayList<String>()
        for (team in teamService.getTeams()) {
            teamNames.add(team.name)
        }
        return teamNames
    }

    @PostMapping("/teams")
    fun createTeam(@RequestBody teamName: String): ResponseEntity<String> {
        val savedTeam = teamService.createTeam(Team(name = teamName))
        val location = createResourceLocation("/teams", savedTeam.id)
        return ResponseEntity.created(location).body(savedTeam.name)
    }

    private fun createResourceLocation(path: String, resourceId: Long): URI {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().port("8080").path(path)
            .buildAndExpand(resourceId).toUri()
    }
}
