import {IBoard} from "../../../../models/Board";

export type BoardProp = {
    board: IBoard,
    deleteBoard: (id: number) => void
    updateSelectedBoardId: (id: number) => void
}