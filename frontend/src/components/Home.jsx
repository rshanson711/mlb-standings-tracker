import { For, onMount} from "solid-js";
import { createStore } from "solid-js/store"
import { Table } from "solid-bootstrap"
import styles from "../css/Home.module.css"
import axios from "axios";
import TeamRecord from "./TeamRecord";

const [leagueDivisionStandings, setLeagueDivisionStandings] = createStore([]);
const addLeagueDivisionStanding = (leagueDivisionStanding => {
    setLeagueDivisionStandings([...leagueDivisionStandings, leagueDivisionStanding]);
});

onMount(async () => {
    const request = await axios.get("/api").then(response => {
        response.data.forEach(e => {
            console.log(e);
            addLeagueDivisionStanding(e);
        });
        console.log(leagueDivisionStandings[0][0].teams[0].team.name);
    })
});

function Home() {
    return (
        <div class="container-fluid">
            <button onClick={() => getStandings()}>Button</button>
            <div class="row">
                <div class="col-md-6 text-center">
                    {/* <h2>{alLeader}</h2> */}
                </div>
                <div class="col-md-6 text-center">
                    {/* <h2>{nlLeader}</h2> */}
                </div>
            </div>
            <div class="row">
                <For each={leagueDivisionStandings}>{(leagueDivisionStanding) =>
                    <div class="col-md-6 standings-col">
                    <For each={leagueDivisionStanding}>{(divisionStanding) =>
                        <Table bordered hover style="width: 55%">
                            <thead>
                                <tr>
                                    <th class="w-10"></th>
                                    <th class="w-25">{divisionStanding.name}</th>
                                    <th class="w-10">W</th>
                                    <th class="w-10">L</th>
                                    <th class="w-10">Pct.</th>
                                    <th class="w-10">GB</th>
                                </tr>
                            </thead>
                            <tbody>
                                <For each={divisionStanding.teams}>{(teamRecord) =>
                                    <TeamRecord teamRecord={teamRecord} teamId={teamRecord.team.id}/>
                                }
                                </For>
                            </tbody>
                        </Table>
                    }   
                    </For>
                    </div> 
                }
                </For>
            </div>
        </div>
    )
}

export default Home;