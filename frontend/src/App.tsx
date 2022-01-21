import React, { FormEvent, useEffect, useState } from "react";
import "./App.css";
import { createTeam, getTeams } from "./teamsApiClient";

function App() {
  const [teams, setTeams] = useState<string[]>([]);
  const [teamName, setTeamName] = useState<string>("");

  const setTeamNameFromInput = (e: FormEvent<HTMLInputElement>) => {
    setTeamName(e.currentTarget.value);
  };

  const submitForm = (e: FormEvent) => {
    e.preventDefault();
    createTeam(teamName).then(() => {
      getTeams().then(setTeams);
    });
  };

  useEffect(() => {
    getTeams().then(setTeams);
  }, []);

  return (
    <>
      <ul>
        {teams.map((team, i) => (
          <li key={i}>{team}</li>
        ))}
      </ul>

      <form onSubmit={submitForm}>
        <label>
          Team Name
          <input name="team-name" type="text" onChange={setTeamNameFromInput} />
        </label>
        <button type="submit">Submit</button>
      </form>
      <form>
        <label>
          First Name
          <input name="first-name" type="text" required={true}/>
        </label>
        <label>
          Last Name
          <input name="last-name" type="text" required={true}/>
        </label>
      </form>
    </>
  );
}

export default App;
