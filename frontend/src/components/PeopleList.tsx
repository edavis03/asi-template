import {Person} from "../Api/peopleApiClient";
import {Team} from "../Api/teamsApiClient";
import {ChangeEvent} from "react";

interface PeopleListProps {
  teamMembers: Person[]
  teams: Team[]
  changeTeam: (personId: number, teamId: number) => void
}

export const PeopleList = ({teamMembers, teams, changeTeam}: PeopleListProps) => {

  const handleClick = (personId: number, teamId: ChangeEvent) => {
    changeTeam(personId, +teamId)
  }

  return (
    <ul>
      {teamMembers.map((person) => (
        <li key={person.id}>
          {person.name}
          <select onChange={(event) => handleClick(person.id, event)}>
            {teams.map(team => (
              <option key={team.id} value={team.id}>
                {team.name}
              </option>
            ))}
          </select>
        </li>

      ))}
    </ul>
  )
}