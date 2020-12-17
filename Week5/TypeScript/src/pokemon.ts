import Ability from './ability';
import Move from './move';

export default class Pokemon {
    id: number;
    name: string;
    abilities: Ability[];
    moves: Move[];
    sprites: {
        back_default: string;
        back_female: string;
        back_shiny: string;
        back_shiny_female: string;
        front_default: string;
        front_female: string;
        front_shiny: string;
        front_shiny_female: string;
    };
}