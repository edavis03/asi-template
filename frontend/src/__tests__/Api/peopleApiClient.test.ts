import nock from "nock";
import {changeTeams, createPerson, getPeople} from "../../Api/peopleApiClient";

describe('peopleApiClient', () => {
  it('should make a GET request to get people when getPeople is called', async () => {
    const expectedPeople = [{id: 1, name: 'Easton White'}, {id: 2, name: 'Colton White'}]
    nock('http://localhost').get('/person').reply(200, expectedPeople)

    const people = await getPeople()

    expect(people).toEqual(expectedPeople)
  });

  it('should make a POST request to create a person when createPerson is called', async () => {
    const scope = nock('http://localhost', {
      reqheaders: {
        'Content-Type': 'text/plain'
      }
    }).post('/createPerson', 'Easton White')
      .reply(200, {id: '1', name: 'Easton White'})

    const response = await createPerson("Easton White")
    expect(scope.isDone()).toEqual(true)
    expect(response).toEqual({id: "1", name: "Easton White"})
  });

  it('should make a POST request to change a persons team when changeTeam is called', async () => {
    const scope = nock('http://localhost')
      .post('/changeTeam\/1\/2')
      .reply(200, {id: "1", name: "Sidney Hall", teamId: "2"})

    const response = await changeTeams(1, 2)

    expect(scope.isDone()).toEqual(true)
    expect(response).toEqual({id: "1", name: "Sidney Hall", teamId: "2"})
  });
})
