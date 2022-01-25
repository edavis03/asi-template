package mil.army.futures.asitemplate.controllers

import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.TeamRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamController(private val teamRepository: TeamRepository) {

    @GetMapping("/teams")
    fun getTeam(): List<String> {
        return teamRepository.findAll().map { it.name }
    }

    @PostMapping("/team")
    fun createTeam(@RequestBody teamName: String): String {
        return teamRepository.save(Team(name = teamName)).name
    }
}