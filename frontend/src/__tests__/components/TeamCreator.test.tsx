import {render, screen} from "@testing-library/react";
import {TeamCreator} from "../../components/TeamCreator";
import userEvent from "@testing-library/user-event";
import {createTeam} from "../../Api/teamsApiClient";

jest.mock("../../api/teamsApiClient");

const getTeamsApiClient = createTeam as jest.MockedFunction<typeof createTeam>;

describe('TeamCreator', () => {
  it('should have a name input and Create Team button', () => {
    const mockRefreshTeams = jest.fn()

    render(<TeamCreator refreshTeams={mockRefreshTeams}/>)

    expect(screen.getByLabelText('Team Name')).toBeVisible()
    expect(screen.getByRole('button', {name: 'Create Team'})).toBeVisible()
  });

  it('should call the refreshTeams prop 1 time after a team is created', async () => {
    getTeamsApiClient.mockResolvedValue("Goat Team");
    const mockRefreshTeams = jest.fn()

    render(<TeamCreator refreshTeams={mockRefreshTeams}/>)

    userEvent.type(await screen.findByLabelText('Team Name'), 'Goat Team')
    userEvent.click(screen.getByRole('button', {name: 'Create Team'}))

    expect(await getTeamsApiClient).toBeCalledTimes(1)
    expect(mockRefreshTeams).toBeCalledTimes(1)
  });
})