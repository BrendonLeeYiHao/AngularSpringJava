export class User {

    id: number;
    name: string;
    password: string;
    email: string;
    dob: string | null;
    gender: string

    constructor(
        id: number,
        name: string,
        password: string,
        email: string,
        dob: string,
        gender: string
    ) {
        this.id = id,
        this.name = name,
        this.password = password,
        this.email = email,
        this.dob = dob;
        this.gender = gender
    }
}