import { ITag } from "./tag.model"
import { IUser } from "./user.model"

export interface ITweet {
    tweetId: number,
    nameWithHandle: string,
    date: Date,
    message: string
    user: IUser,
    tagSet: ITag[],
    parentTweet: ITweet,
    replies: ITweet[]
}