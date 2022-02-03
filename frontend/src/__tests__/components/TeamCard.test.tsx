import {render, screen} from "@testing-library/react";
import {TeamCard} from "../../components/TeamCard";
import {Team} from "../../Api/teamsApiClient";
import {Person} from "../../Api/peopleApiClient";

describe("TeamCard", () => {
  it('should display the name of the team', () => {
    const team: Team = {
      name: "Goat Team"
    }

    const teamMembers: Person[] = [
      {id: 1, name: "Colton White", teamId: 1},
      {id: 2, name: "Easton White", teamId: 1}]

    render(<TeamCard team={team} teamMembers={teamMembers}/>)

    expect(screen.getByText('Goat Team')).toBeVisible()
  });
})