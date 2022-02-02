import axios from "axios";

export interface Team {
  id?: number,
  name: string
}

export async function createTeam(teamName: string): Promise<string> {
  return (await axios.post<string>("/team", teamName, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function getTeams(): Promise<Team[]> {
  return (await axios.get<Team[]>("/teams")).data
}
