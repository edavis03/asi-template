import {render, screen} from "@testing-library/react";
import {PeopleCreator} from "../../components/PeopleCreator";
import {createPerson} from "../../Api/peopleApiClient";
import userEvent from "@testing-library/user-event";

jest.mock("../../api/peopleApiClient")

const getPeopleApiClient = createPerson as jest.MockedFunction<typeof createPerson>

describe("PeopleCreator", () => {
  it('should have a name field and create person button', () => {

    render(<PeopleCreator refreshTeams={jest.fn()}/>)

    expect(screen.getByLabelText('Person Name')).toBeVisible()
    expect(screen.getByRole('button', {name: "Create Person"})).toBeVisible()
  });

  it('should save the person when create person is clicked and call refresh function', async () => {
    getPeopleApiClient.mockResolvedValue({id: 1, name: "Colton White"})
    const mockRefreshTeams = jest.fn()

    render(<PeopleCreator refreshTeams={mockRefreshTeams}/>)

    userEvent.type(await screen.findByLabelText("Person Name"), "Colton White")
    userEvent.click(screen.getByRole('button', {name: "Create Person"}))

    expect(await getPeopleApiClient).toBeCalledTimes(1)
    expect(mockRefreshTeams).toBeCalledTimes(1)
  });
})