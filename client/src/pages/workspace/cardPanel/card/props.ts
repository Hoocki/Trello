import {ICard, ICardModification} from "../../../../models/Card";

export type CardProp = {
    card: ICard,
    updateCard: (id: number, updateCard: ICardModification) => void
    deleteCard: (id: number) => void
}