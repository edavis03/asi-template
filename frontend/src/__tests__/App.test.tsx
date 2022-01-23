import React from "react";
import {getTeams} from "../api/teamsApiClient";

jest.mock("../teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("Teams Page", () => {
  describe("when the page loads", () => {
    // it("requests the teams from the api", async () => {
    //   getTeamsApiClient.mockResolvedValue([{name: "first-team"}, {name: "second-team"}]);
    //
    //   render(<App/>);
    //
    //   const listItems = await screen.findAllByRole("listitem");
    //   expect(listItems[0].innerHTML).toEqual("first-team");
    //   expect(listItems[1].innerHTML).toEqual("second-team");
    // });
  });


});
