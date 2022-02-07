import {render, screen} from "@testing-library/react";
import {PeopleList} from "../../components/PeopleList";
import {Person} from "../../Api/peopleApiClient";
import {Team} from "../../Api/teamsApiClient";
import userEvent from "@testing-library/user-event";

describe("PeopleList", () => {
  it('should render a list of people', async () => {
    const teamMembers: Person[] = [{id: 1, name: "Josh White", teamId: 1}, {id: 2, name: "Rob Payne", teamId: 1}]
    const teams: Team[] = [{id: 1, name: "Unallocated"}]
    const changeTeam = jest.fn()

    render(<PeopleList teamMembers={teamMembers} teams={teams} changeTeam={changeTeam}/>)

    const people = await screen.findAllByRole('listitem')

    expect(people[0].innerHTML).toContain("Josh White")
    expect(people[1].innerHTML).toContain("Rob Payne")
  });

  it('should display a dropdown with a list of team', async () => {
    const teamMembers: Person[] = [{id: 1, name: "Josh White", teamId: 1}, {id: 2, name: "Rob Payne", teamId: 1}]
    const teams: Team[] = [{id: 1, name: "Unallocated"}, {id: 2, name: "Goat Team"}]
    const changeTeam = jest.fn()

    render(<PeopleList teamMembers={teamMembers} teams={teams} changeTeam={changeTeam}/>)

    const teamChanger = await screen.findAllByRole('option')

    expect(teamChanger).toHaveLength(4)
  });

  it('should change a users team when selecting another team', async () => {
    const teamMembers: Person[] = [{id: 1, name: "Josh White", teamId: 1}]
    const teams: Team[] = [{id: 1, name: "Unallocated"}, {id: 2, name: "Goat Team"}]
    const changeTeam = jest.fn()
    render(<PeopleList teamMembers={teamMembers} teams={teams} changeTeam={changeTeam}/>)

    const teamSelectOption = await screen.findByRole('option', {name: "Goat Team"})

    expect(teamSelectOption).not.toBeChecked()

    userEvent.click(teamSelectOption)
    expect(changeTeam).toHaveBeenCalledTimes(1)

  });
})