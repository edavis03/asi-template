import {when} from "jest-when";
import {createTeam, getTeams} from "../../api/teamsApiClient";
import {render, screen} from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import React from "react";
import {TeamCreator} from "../../components/TeamCreator";

jest.mock("../../api/teamsApiClient");
const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("creating team", () => {

  it("appends the team name to the list", async () => {
    const mockUpdateTeams = jest.fn()
    when(createTeam)
      .calledWith("example-team-name")
      .mockResolvedValueOnce("example-team-name");

    getTeamsApiClient.mockResolvedValueOnce([]);
    getTeamsApiClient.mockResolvedValueOnce([{name: "example-team-name"}]);

    render(<TeamCreator updateTeams={mockUpdateTeams}/>);

    userEvent.type(screen.getByLabelText("Team Name"), "example-team-name");
    userEvent.click(screen.getByRole("button", {name: /submit/i}));
    expect(createTeam).toBeCalledWith("example-team-name")
  });
});