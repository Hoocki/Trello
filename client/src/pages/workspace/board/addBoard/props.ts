import {IBoardModification} from "../../../../models/Board";

export type AddBoardProp = {
    onCreate: (board: IBoardModification) => void
}