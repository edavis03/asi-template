import {Team} from "../teamsApiClient";

export interface TeamCardProps {
  team: Team
}

export const TeamCard = (props: TeamCardProps) => {
  return (
    <li>
      {props.team.name}
    </li>
  )
}