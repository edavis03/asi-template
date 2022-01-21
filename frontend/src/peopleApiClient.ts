import axios from "axios";

interface Person {
  firstName: string
  lastName: string
}

export async function getPeople(): Promise<Person[]> {
  return (await axios.get<Person[]>("/people")).data
}

export async function createPerson(person: Person): Promise<Person> {
  return (await axios.post<Person>("/people", {firstName: person.firstName, lastName: person.lastName}, {headers: {'Content-Type': 'text/plain'}})).data
}