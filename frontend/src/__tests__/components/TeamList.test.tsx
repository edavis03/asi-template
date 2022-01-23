import {render, screen} from "@testing-library/react";
import React from "react";
import {TeamList} from "../../components/TeamList";
import {Team} from "../../App";


describe("TeamList", () => {
    it("when the page loads requests the teams from the api", async () => {

      const teams: Team[] = [{name: "first-team"}, {name: "second-team"}]

      render(<TeamList teams={teams}/>);


      const listItems = await screen.findAllByRole("listitem");
      expect(listItems[0].innerHTML).toEqual("first-team");
      expect(listItems[1].innerHTML).toEqual("second-team");
    });
  });
