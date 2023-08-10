import { Card } from "solid-bootstrap";
import { createSignal } from "solid-js";
import styles from "../css/TeamCard.module.css"

function TeamCard(props) {
    return (
        <Card class={styles.card}>
            <Card.Img variant='top' src={"../src/assets/images/" + props.team.id + "/primary.png"} class={styles.cardImage}></Card.Img>
            <Card.Body>
                <h2>{props.team.name}</h2>
            </Card.Body>
        </Card>
    )
}

export default TeamCard;