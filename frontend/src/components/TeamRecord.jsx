import { createStore } from "solid-js/store"
import styles from "../css/TeamRecord.module.css"

export default function TeamRecord(props) {
    const [img, setImg] = createStore({
        src: "../src/assets/images/" + props.teamId + "/primary.png"
    });

    return (
        <tr>
            <td>
                <div class={styles.logoWrapper}>
                    <img src={img.src} style="height: 100%; width: 100%; object-fit: contain;"/>
                </div>
            </td>
            <td class="align-middle">{props.teamRecord.team.name}</td>
            <td class="align-middle">{props.teamRecord.wins}</td>
            <td class="align-middle">{props.teamRecord.losses}</td>
            <td class="align-middle">{props.teamRecord.winningPercentage}</td>
            <td class="align-middle">{props.teamRecord.gamesBack}</td>
        </tr>
    )
}