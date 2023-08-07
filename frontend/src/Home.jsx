import { For, onMount} from "solid-js";
import { createStore } from "solid-js/store"
import styles from "./Home.module.css"
import axios from "axios";
import logo from "./assets/images/109/primary.png"

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
                        <table class="table" style="width: 55%">
                            <tbody>
                                <tr>
                                    <th class="w-10"></th>
                                    <th class="w-25">{divisionStanding.name}</th>
                                    <th class="w-10">W</th>
                                    <th class="w-10">L</th>
                                    <th class="w-10">Pct.</th>
                                    <th class="w-10">GB</th>
                                </tr>
                                <For each={divisionStanding.teams}>{(teamRecord) =>
                                    <tr>
                                        <td>
                                            <div class={styles.logoWrapper}>
                                                <img src='/assets/images/109/primary.png' style="height: 100%; width: 100%; object-fit: contain;"/>
                                            </div>
                                        </td>
                                        <td class="align-middle">{teamRecord.team.name}</td>
                                        <td class="align-middle">{teamRecord.wins}</td>
                                        <td class="align-middle">{teamRecord.losses}</td>
                                        <td class="align-middle">{teamRecord.winningPercentage}</td>
                                        <td class="align-middle">{teamRecord.gamesBack}</td>
                                    </tr>
                                }
                                </For>
                            </tbody>
                        </table>
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