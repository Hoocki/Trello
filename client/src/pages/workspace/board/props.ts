import {IBoard} from "../../../models/Board";

export type DeleteBoardProp = {
    board: IBoard,
    onDelete: (id: number) => void
}