import { Card } from "solid-bootstrap";
import { createSignal } from "solid-js";
import styles from "../css/TeamCard.module.css"

const addControls = (e) => {
    e.target.controls = true;
}

const removeControls = (e) => {
    e.target.controls = false;
}

function TeamCard(props) {
    return (
        <Card class="mh-50" style="height: 377px;">
            <div class={styles.cardVideoWrapper}>
                <Show when={props.videoURL !== null}>
                    <video class={styles.cardVideo} onmouseover={(event) => addControls(event)} onmouseleave={(event) => removeControls(event)}>
                        <source src={props.videoURL}></source>
                    </video>
                </Show>
            </div>
            <Card.Body>
                <h2>{props.team.name}</h2>
            </Card.Body>
        </Card>
    )
}

export default TeamCard;