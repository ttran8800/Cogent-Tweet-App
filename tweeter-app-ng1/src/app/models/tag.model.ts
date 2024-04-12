import { ITweet } from "./tweet.model"

export interface ITag{
    tagName: string,
    tweetSet: ITweet[]
}