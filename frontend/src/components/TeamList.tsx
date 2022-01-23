import React from "react";
import {Team} from "../App";


export interface TeamListProps {
  teams: Team[]
}

export const TeamList = (props: TeamListProps) => {


  return (
    <>
      <ul>
        {props.teams.map((team, i) => (
          <li key={i}>{team.name}</li>
        ))}
      </ul>
    </>
  );
}