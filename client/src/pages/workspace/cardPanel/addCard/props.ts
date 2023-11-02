import {ICardModification} from "../../../../models/Card";

export type AddCardProp = {
    selectedBoardId: number
    addCard: (card: ICardModification) => void
}