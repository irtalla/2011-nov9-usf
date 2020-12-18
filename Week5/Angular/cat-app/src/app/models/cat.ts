import { Breed } from "./breed";
import { SpecialNeed } from "./specialneed";
import { Status } from "./status";

export class Cat {
    id: number;
    name: string;
    age: number;
    breed: Breed;
    status: Status;
    specialNeeds: SpecialNeed[];
}
