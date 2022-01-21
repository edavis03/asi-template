import nock from "nock";
import {createPerson, getPeople} from "../peopleApiClient";

describe('peopleApiClient', () => {
  it('should should make a GET request to get people when getPeople is called', async () => {
    const expectedPeople = [{firstName: 'Josh', lastName: 'White'}, {firstName: 'Colton', lastName: 'White'}]
    nock('http://localhost').get('/people').reply(200, expectedPeople)

    const people = await getPeople()

    expect(people).toEqual(expectedPeople)
  });

  it('should make a POST request to create a person when createPerson is called', async () => {
    const scope = nock('http://localhost', {
      reqheaders: {
        'Content-Type': 'text/plain'
      }
    }).post('/people', {firstName: 'Josh', lastName: 'White'})
      .reply(200, {firstName: 'Josh', lastName: 'White'})

    const response = await createPerson({firstName: 'Josh', lastName: 'White'})

    expect(scope.isDone()).toEqual(true)
    expect(response).toEqual({firstName: 'Josh', lastName: 'White'})
  });
} )