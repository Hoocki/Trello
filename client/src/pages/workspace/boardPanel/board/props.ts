import {IBoard, IBoardModification} from "../../../../models/Board";

export type BoardProp = {
    board: IBoard,
    deleteBoard: (id: number) => void
    updateBoard: (id: number, updateBoard: IBoardModification) => void
    updateSelectedBoardId: (id: number) => void
}