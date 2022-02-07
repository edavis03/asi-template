import axios from "axios";

export interface Person {
  id: number,
  name: string
  teamId: number
}

export async function getPeople(): Promise<Person[]> {
  return (await axios.get<Person[]>("/person")).data
}

export async function createPerson(personName: string): Promise<Person> {
  return (await axios.post('/createPerson',
    personName, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function changeTeams(personId: number, teamId: number): Promise<Person> {
  return (await axios.post(`/changeTeam/${personId}/${teamId}`)).data
}