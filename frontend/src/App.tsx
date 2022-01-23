import React, {useEffect, useState} from "react";
import "./App.css";
import {getTeams} from "./api/teamsApiClient";
import {TeamCreator} from "./components/TeamCreator";
import {TeamList} from "./components/TeamList";
import {PersonCreator} from "./components/PersonCreator";

export interface Team {
  name: string
}

function App() {
  const [teams, setTeams] = useState<Team[]>([]);
  const [teamRefresh, setTeamRefresh] = useState(1)

  const updateTeams = () => {
    setTeamRefresh(prevState => prevState + 1)
  }

  useEffect(() => {
    getTeams().then(setTeams);
  }, [teamRefresh]);

  return (
    <>
      <div>
        <TeamCreator updateTeams={updateTeams}/>
        <PersonCreator/>
      </div>
      <div>
        <TeamList teams={teams}/>
      </div>
    </>
  );
}

export default App;
