import {Team} from "../Api/teamsApiClient";
import {Person} from "../Api/peopleApiClient";
import {PeopleList} from "./PeopleList";

export interface TeamCardProps {
  team: Team
  teamMembers: Person[]
}

export const TeamCard = ({team, teamMembers}: TeamCardProps) => {
  return (
    <li>
      {team.name}
      <PeopleList teamMembers={teamMembers}/>
    </li>

  )
}