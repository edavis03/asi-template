import {render, screen} from "@testing-library/react";
import {PeopleList} from "../../components/PeopleList";
import {Person} from "../../Api/peopleApiClient";

describe("PeopleList", () => {
  it('should render a list of people', async () => {
    const teamMembers: Person[] = [{id: 1, name: "Josh White"}, {id: 2, name: "Rob Payne"}]

    render(<PeopleList teamMembers={teamMembers}/>)

    const people = await screen.findAllByRole('listitem')

    expect(people[0].innerHTML).toEqual("Josh White")
    expect(people[1].innerHTML).toEqual("Rob Payne")
  });
})