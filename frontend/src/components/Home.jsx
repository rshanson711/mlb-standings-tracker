import { For, onMount} from "solid-js";
import { createStore } from "solid-js/store"
import { Table, Accordion } from "solid-bootstrap"
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
                    <Accordion class="col-md-6" defaultActiveKey="0">
                        <For each={leagueDivisionStanding}>{(divisionStanding, index) =>
                        <Accordion.Item eventKey={index}>
                            <Accordion.Header>{divisionStanding.name}</Accordion.Header>
                            <Accordion.Body>
                                <Table bordered hover style="width: 55%">
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
                                            <TeamRecord teamRecord={teamRecord} teamId={teamRecord.team.id}/>
                                            //TODO: POPUP CARD WHEN CLICKING ON TEAM ROW
                                        }
                                        </For>
                                    </tbody>
                                </Table>
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