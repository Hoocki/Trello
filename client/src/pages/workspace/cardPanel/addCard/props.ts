import {ICardModification} from "../../../../models/Card";

export type AddCardProp = {
    selectedBoardId: number
    addCard: (boardId: number, card: ICardModification) => void
}