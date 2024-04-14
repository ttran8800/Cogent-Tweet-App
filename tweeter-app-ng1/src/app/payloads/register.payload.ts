import { IUser } from "../models/user.model";

export interface RegisterPayload {
    error: boolean,
    errorType: string,
    message: string,
    user: IUser
}