import React, {FormEvent, useState} from "react";
import {createPerson} from "../Api/peopleApiClient";

export interface PeopleCreatorProps {
  refreshTeams: () => void
}

export const PeopleCreator = ({refreshTeams}: PeopleCreatorProps) => {
  const [name, setName] = useState<string>("")

  function setPersonName(event: FormEvent<HTMLInputElement>) {
    event.preventDefault()
    setName(event.currentTarget.value)
  }

  function makePerson(event: FormEvent) {
    event.preventDefault()
    createPerson(name)
      .then(() => refreshTeams())
  }

  return (
    <form onSubmit={makePerson}>
      <label >
        Person Name
        <input name="person-name" type="text" onChange={setPersonName}/>
      </label>
      <button type="submit">Create Person</button>
    </form>
  )
}