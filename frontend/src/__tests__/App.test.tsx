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
});
