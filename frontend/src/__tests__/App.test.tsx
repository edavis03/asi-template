import React from "react";
import {render, waitFor} from "@testing-library/react";
import App from "../App";
import {getTeams} from "../Api/teamsApiClient";

jest.mock("../api/teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("Teams Page", () => {
  describe("when the page loads", () => {
    it("requests the teams from the api", async () => {
      getTeamsApiClient.mockResolvedValue([{id: 1, name: "first-team"}, {id: 2, name:"second-team"}]);

      render(<App/>)

      await waitFor(() => expect(getTeamsApiClient).toBeCalledTimes(1))

    });
  });

  // describe("creating", () => {
  //
  //   it("appends the team name to the list", async () => {
  //     when(createTeam)
  //       .calledWith("example-team-name")
  //       .mockResolvedValueOnce("example-team-name");
  //
  //     getTeamsApiClient.mockResolvedValueOnce([]);
  //     getTeamsApiClient.mockResolvedValueOnce([{id: 1, name: "example-team-name"}]);
  //
  //     render(<App/>);
  //
  //     userEvent.type(screen.getByLabelText("Team Name"), "example-team-name");
  //     userEvent.click(screen.getByRole("button", {name: /submit/i}));
  //     expect(await screen.findByText("example-team-name")).toBeVisible();
  //   });
  // });
});
