import {Team} from "../Api/teamsApiClient";
import {Person} from "../Api/peopleApiClient";
import {PeopleList} from "./PeopleList";

export interface TeamCardProps {
  team: Team
  teamMembers: Person[]
  teams: Team[]
  changeTeam: (personId: number, teamId: number) => void
}

export const TeamCard = ({team, teamMembers, teams, changeTeam}: TeamCardProps) => {
  return (
    <li>
      {team.name}
      <PeopleList teamMembers={teamMembers} teams={teams} changeTeam={changeTeam}/>
    </li>

  )
}