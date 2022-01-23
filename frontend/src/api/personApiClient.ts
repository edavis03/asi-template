import axios from "axios";

export interface Person {
  firstName: string
  lastName: string
}

export async function createPerson(person: Person): Promise<Person> {
  return (await axios.post<Person>("/person", person, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function getPeople(): Promise<Person[]> {
  return (await axios.get<Person[]>("/person")).data
}