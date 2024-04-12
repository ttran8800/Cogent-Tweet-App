import { IRole } from "./role.model";
import { ITweet } from "./tweet.model";

export interface IUser {
    firstName: string,
    lastName: string,
    email: string,
    loginId: string,
    password: string,
    contactNumber: string,
    roles: IRole[],
    tweets: ITweet[]
}