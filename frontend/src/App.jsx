import logo from './logo.svg';
import styles from './App.module.css';
import { createSignal } from 'solid-js';

const [name, setName] = createSignal('Test');

async function test() {
  const response = await(fetch('/api/', {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    }
  }));

  console.log(response);
  const body = await response.json();
  console.log(body);
  console.log(body.body);
  setName(body.body);
}

function App() {
  return (
    <div class={styles.App}>
      <header class={styles.header}>
        <img src={logo} class={styles.logo} alt="logo" />
        <p>
          Edit <code>src/App.jsx</code> and save to reload.
        </p>
        <a class={styles.link} href="https://github.com/solidjs/solid" target="_blank" rel="noopener noreferrer">
          Learn Solid!~
        </a>
      </header>
        <div class='test'>
          <button onClick={test()}></button>
          {name()}
        </div>
    </div>
  );
}

export default App;
