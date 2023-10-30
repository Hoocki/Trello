import {IBoardModification} from "../../../../models/Board";

export type AddBoardProp = {
    addBoard: (board: IBoardModification) => void
}