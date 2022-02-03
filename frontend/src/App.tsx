import React, {useEffect, useState} from "react";
import "./App.css";
import {getTeams, Team} from "./Api/teamsApiClient";
import {TeamCard} from "./components/TeamCard";
import {TeamCreator} from "./components/TeamCreator";
import {PeopleCreator} from "./components/PeopleCreator";
import {getPeople, Person} from "./Api/peopleApiClient";

function App() {
  const [teams, setTeams] = useState<Team[]>([]);
  const [people, setPeople] = useState<Person[]>([])
  const [refresh, setRefresh] = useState<number>(0)

  const refreshTeams = () => {
    setRefresh(prevState => prevState + 1)
  }

  useEffect(() => {
    getTeams().then(setTeams);
    getPeople().then(setPeople)
  }, [refresh]);

  return (
    <>
      <div>
        <ul>
          {teams.map((team) => (
            <TeamCard key={team.id} team={team} teamMembers={people.filter(person => {
              return person.teamId === team.id
            })}/>
          ))}
        </ul>
      </div>
      <div>
        <TeamCreator refreshTeams={refreshTeams}/>
        <PeopleCreator refreshTeams={refreshTeams}/>
      </div>
    </>
  );
}

export default App;
