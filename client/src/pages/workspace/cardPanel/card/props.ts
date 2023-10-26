import {ICard} from "../../../../models/Card";

export type CardProp = {
    card: ICard,
    deleteCard: (id: number) => void
}