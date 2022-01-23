import {render, screen} from "@testing-library/react";
import {PersonCreator} from "../../components/PersonCreator";
import userEvent from "@testing-library/user-event";
import {createPerson} from "../../api/personApiClient";
import {when} from "jest-when";

jest.mock("../../api/personApiClient");
const getPersonApiClient = createPerson as jest.MockedFunction<typeof createPerson>;

describe("PersonCreator", () => {
  it('should have the correct fields', () => {
    render(<PersonCreator/>)

    expect(screen.getByLabelText('First Name')).toBeVisible()
    expect(screen.getByLabelText('Last Name')).toBeVisible()
    expect(screen.getByRole('button', {name: 'Create Person'}))
  });

  it('should create a person and put them in an unallocated team', () => {
    when(createPerson)
      .calledWith({firstName: "Josh", lastName: "White"})
      .mockResolvedValueOnce({firstName: "Josh", lastName: "White"});

    render(<PersonCreator/>)

    userEvent.type(screen.getByLabelText('First Name'), "Josh")
    userEvent.type(screen.getByLabelText('Last Name'), "White")
    userEvent.click(screen.getByRole('button', {name: "Create Person"}))

    expect(createPerson).toBeCalledTimes(1)
  });
})