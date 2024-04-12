import { ITag } from "./tag.model"
import { IUser } from "./user.model"

export interface ITweet {
    nameWithHandle: string,
    date: Date,
    message: string
    user: IUser,
    tagSet: ITag[],
    parentTweet: ITweet
}