export default class Ability {
    ability: {
        name: string;
        url: string;
        is_hidden: boolean;
    }

    constructor() {
        this.ability = {
            name: 'Name',
            url: 'Url',
            is_hidden: false
        }
    }
}