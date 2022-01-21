import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {when} from "jest-when";
import {createTeam, getTeams} from "../teamsApiClient";
import {getPeople} from "../peopleApiClient";

jest.mock("../teamsApiClient");
jest.mock("../peopleApiClient")

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;
// const getPeopleApiClient = getPeople as jest.MockedFunction<typeof getPeople>

describe("Teams Page", () => {
  describe("when the page loads", () => {
    it("requests the teams from the api", async () => {
      getTeamsApiClient.mockResolvedValue(["first-team", "second-team"]);

      render(<App/>);

      const listItems = await screen.findAllByRole("listitem");
      expect(listItems[0].innerHTML).toEqual("first-team");
      expect(listItems[1].innerHTML).toEqual("second-team");
    });
  });

  describe("creating team", () => {

    it("appends the team name to the list", async () => {
      when(createTeam)
        .calledWith("example-team-name")
        .mockResolvedValueOnce("example-team-name");

      getTeamsApiClient.mockResolvedValueOnce([]);
      getTeamsApiClient.mockResolvedValueOnce(["example-team-name"]);

      render(<App/>);

      userEvent.type(screen.getByLabelText("Team Name"), "example-team-name");
      userEvent.click(screen.getByRole("button", {name: /submit/i}));
      expect(await screen.findByText("example-team-name")).toBeVisible();
    });
  });

  describe("creating a person", () => {
    it('should contain the required fields to create a person', async () => {
      getTeamsApiClient.mockResolvedValue([]);
 
      render(<App/>)

      expect(await screen.getByLabelText(/First Name/i)).toBeVisible()
      expect(await screen.getByLabelText(/First Name/i)).toBeRequired()
      expect(await screen.getByLabelText(/Last Name/i)).toBeVisible()
      expect(await screen.getByLabelText(/Last Name/i)).toBeRequired()

    });

    it('should disable the submit button ', () => {
      
    });
  })
});
