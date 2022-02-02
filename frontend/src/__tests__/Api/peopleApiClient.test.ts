import nock from "nock";
import {createPerson, getPeople} from "../../Api/peopleApiClient";

describe('peopleApiClient', () => {
  it('should make a GET request to get people when getPeople is called', async () => {
    const expectedPeople = [{id: 1, name: 'Easton White'}, {id: 2, name: 'Colton White'}]
    nock('http://localhost').get('/person').reply(200, expectedPeople)

    const people = await getPeople()

    expect(people).toEqual(expectedPeople)
  });

})
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
