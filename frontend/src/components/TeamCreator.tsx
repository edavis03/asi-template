import React, {FormEvent, useState} from "react";
import {createTeam} from "../Api/teamsApiClient";

export interface TeamCreatorProps {
  refreshTeams: () => void
}

export const TeamCreator = (props: TeamCreatorProps) => {

  const [teamName, setTeamName] = useState<string>("");

  const setTeamNameFromInput = (e: FormEvent<HTMLInputElement>) => {
    setTeamName(e.currentTarget.value);
  };

  const makeTeam = (event: FormEvent) => {
    event.preventDefault();
    createTeam(teamName)
      .then(() => props.refreshTeams())
  };

  return (
    <form onSubmit={makeTeam}>
      <label>
        Team Name
        <input name="team-name" type="text" onChange={setTeamNameFromInput} />
      </label>
      <button type="submit">Create Team</button>
    </form>
  )
}