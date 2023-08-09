import { Card } from "solid-bootstrap";
import { createStore } from "solid-js/store";

function TeamCard(props) {
    const [img, setImg] = createStore({
        src: "../src/assets/images/" + props.teamId + "/primary.png"
    });

    return (
        <Card>
            <Card.Img variant='top'></Card.Img>
            <Card.Body>

            </Card.Body>
        </Card>
    )
}

export default TeamCard;