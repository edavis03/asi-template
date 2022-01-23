import React, {FormEvent, useState} from "react";
import {createTeam, getTeams} from "../api/teamsApiClient";

export interface TeamCreatorProps {
  updateTeams: () => void
}

export const TeamCreator = (props: TeamCreatorProps) => {
  const [teamName, setTeamName] = useState<string>("");

  const setTeamNameFromInput = (e: FormEvent<HTMLInputElement>) => {
    setTeamName(e.currentTarget.value);
  };

  const submitForm = (e: FormEvent) => {
    e.preventDefault();
    createTeam(teamName).then(() => {
      props.updateTeams();
    });
  };

  return (
    <>
      <form onSubmit={submitForm}>
        <label>
          Team Name
          <input name="team-name" type="text" onChange={setTeamNameFromInput} />
        </label>
        <button type="submit">Submit</button>
      </form>
    </>
  );
}