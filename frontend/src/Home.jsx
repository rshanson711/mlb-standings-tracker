import { For } from "solid-js";
import styles from "./Home.module.css"

async function getStandings() {
    const request = await fetch("/api/");
    console.log(request);
    const response = await request.json();
    console.log(response);
}

function Home() {
    return (
        <div class="container-fluid">
            <button onClick={() => getStandings()}>Button</button>
        {/* <div class="row">
            <div class="col-md-6 text-center">
                <h2>{alLeader}</h2>
            </div>
            <div class="col-md-6 text-center">
                <h2>{nlLeader}</h2>
            </div>
        </div>
        <div class="row">
            <For each={divisionStandings}>
                <For each={league}>
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
                            <For each={teamRecord}>
                                <td>
                                    <div class="logo-wrapper">
                                    </div>
                                </td>
                                <td class="align-middle">{teamRecord.team.name}</td>
                                <td class="align-middle">{teamRecord.wins}</td>
                                <td class="align-middle">{teamRecord.losses}</td>
                                <td class="align-middle">{teamRecord.winningPercentage}</td>
                                <td class="align-middle">{teamRecord.gamesBack}</td>
                            </For>
                        </tbody>
                    </table>    
                </For>
            </For>
        </div> */}
    </div>
    )
}

export default Home;