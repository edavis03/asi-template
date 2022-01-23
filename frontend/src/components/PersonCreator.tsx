import {FormEvent, useState} from "react";
import {createPerson} from "../api/personApiClient";

export const PersonCreator = () => {
  const [firstName, setFirstName] = useState<string>('')
  const [lastName, setLastName] = useState<string>('')
  function submitForm(e: FormEvent) {
    e.preventDefault()
    createPerson({firstName: firstName, lastName: lastName})
  }

  function addFirstName(e: FormEvent<HTMLInputElement>) {
    setFirstName(e.currentTarget.value)
  }

  function addLastName(e: FormEvent<HTMLInputElement>) {
    setLastName(e.currentTarget.value)
  }

  return (
    <form onSubmit={submitForm}>
      <label>
        First Name
      <input onChange={addFirstName}/>
      </label>
      <label>
        Last Name
      <input onChange={addLastName}/>
      </label>
      <button type='submit'>Create Person</button>
    </form>
  )
}