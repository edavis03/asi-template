package mil.army.futures.asitemplate.services

import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.TeamRepository
import org.springframework.stereotype.Service

@Service
class TeamService(val teamRepository: TeamRepository) {

    fun createTeam(team: Team): Team {
        return teamRepository.save<Team>(team)
    }

    fun getTeams(): List<Team> {
        return teamRepository.findAll()
    }
}
