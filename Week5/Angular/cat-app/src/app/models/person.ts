import { Cat } from "./cat";
import { Role } from "./role";

export class Person {
    id: number;
    username: string;
    password: string;
    role: Role;
    cats: Cat[];
}
