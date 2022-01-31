import {render, screen} from "@testing-library/react";
import {TeamCard} from "../../components/TeamCard";
import {Team} from "../../teamsApiClient";

describe("TeamCard", () => {
  it('should display the name of the team', () => {
    const team: Team =  {
      name: "Goat Team"
    }

    render(<TeamCard team={team}/>)

    expect(screen.getByText("Goat Team")).toBeVisible()
  });
})