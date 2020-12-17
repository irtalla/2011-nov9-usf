import Pokemon from './pokemon';

let baseUrl: string = 'https://pokeapi.co/api/v2/';

async function getPokemon() {
    let url: string = baseUrl + '73';

    let div: HTMLElement = document.getElementById('main');
    div.innerHTML = url;
    // let response = await fetch(url);
    // if (response.status === 200) {
    //     let pokemon: Pokemon = await response.json();
    //     console.log(pokemon.id);
    //     console.log(pokemon.name);
    //     console.log(pokemon.abilities);
    // }
}