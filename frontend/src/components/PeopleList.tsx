import {Person} from "../Api/peopleApiClient";

interface PeopleListProps {
  teamMembers: Person[]
}

export const PeopleList = ({teamMembers}: PeopleListProps) => {
  return (
    <ul>
      {teamMembers.map((person) => (
        <li key={person.id}>{person.name}</li>
      ))}
    </ul>
  )
}