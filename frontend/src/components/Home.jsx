import { For, onMount} from "solid-js";
import { createStore } from "solid-js/store"
import { createSignal } from "solid-js";
import { Table, Accordion } from "solid-bootstrap"
import styles from "../css/Home.module.css"
import axios from "axios";
import TeamRecord from "./TeamRecord";
import TeamCard from "./TeamCard";

const [leagueDivisionStandings, setLeagueDivisionStandings] = createStore([]);
const addLeagueDivisionStanding = (leagueDivisionStanding => {
    setLeagueDivisionStandings([...leagueDivisionStandings, leagueDivisionStanding]);
});
const [alTeamSelected, setAlTeamSelected] = createSignal();
const [nlTeamSelected, setNlTeamSelected] = createSignal();

onMount(async () => {
    const request = await axios.get("/api").then(response => {
        response.data.forEach(e => {
            addLeagueDivisionStanding(e);
        });
    })
});

const teamCardClickHandler = (team, index) => {
    if (index == 0) {
        setAlTeamSelected(team);
    } else {
        setNlTeamSelected(team);
    }
}

function Home() {
    return (
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 text-center">
                    {/* <h2>{alLeader}</h2> */}
                </div>
                <div class="col-md-6 text-center">
                    {/* <h2>{nlLeader}</h2> */}
                </div>
            </div>
            <div class="row">
                <For each={leagueDivisionStandings}>{(leagueDivisionStanding, leagueIndex) =>
                    <Accordion class="col-md-6" defaultActiveKey="0">
                        <For each={leagueDivisionStanding}>{(divisionStanding, index) =>
                            <Accordion.Item eventKey={index}>
                                <Accordion.Header onClick={() => teamCardClickHandler(null, leagueIndex())}>{divisionStanding.name}</Accordion.Header>
                                <Accordion.Body class="container-fluid">
                                    <div class="row">
                                        <div class="col-6">
                                            <Table bordered hover>
                                                <thead>
                                                    <tr>
                                                        <th class="w-10"></th>
                                                        <th class="w-25"></th>
                                                        <th class="w-10">W</th>
                                                        <th class="w-10">L</th>
                                                        <th class="w-10">Pct.</th>
                                                        <th class="w-10">GB</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <For each={divisionStanding.teams}>{(teamRecord) =>
                                                        <TeamRecord teamRecord={teamRecord} teamId={teamRecord.team.id} onClick={() => teamCardClickHandler(teamRecord.team, leagueIndex())}/>
                                                    }
                                                    </For>
                                                </tbody>
                                            </Table>
                                        </div>
                                        <div class="col-6">
                                            <Show when={alTeamSelected() != null && leagueIndex() == 0}>
                                                <TeamCard team={alTeamSelected()}>

                                                </TeamCard>
                                            </Show>
                                            <Show when={nlTeamSelected() != null && leagueIndex() == 1}>
                                                <TeamCard team={nlTeamSelected()}>

                                                </TeamCard>
                                            </Show>
                                        </div>
                                    </div>
                                </Accordion.Body>
                            </Accordion.Item>
                        }   
                        </For>
                    </Accordion>
                    }
                </For>
            </div>
        </div>
    )
}

export default Home;