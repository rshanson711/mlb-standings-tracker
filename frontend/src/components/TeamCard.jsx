import { Card } from "solid-bootstrap";
import { createSignal } from "solid-js";
import styles from "../css/TeamCard.module.css"

function TeamCard(props) {
    return (
        <Card class="mh-50" style="height: 377px;">
            <div class={styles.cardVideoWrapper}>
                <video class={styles.cardVideo} controls="controls">
                    <source src=""></source>
                </video>
            </div>
            <Card.Body>
                <h2>{props.team.name}</h2>
            </Card.Body>
        </Card>
    )
}

export default TeamCard;