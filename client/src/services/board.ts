import {IBoard, IBoardModification} from "../models/Board";

export const getBoardsService = (): IBoard[] => {
    return [
        {id: 1, name: 'board1', description: 'boardDesc1', createdAt: new Date(), updatedAt: new Date()},
        {id: 2, name: 'board2', description: 'boardDesc2', createdAt: new Date(), updatedAt: new Date()}
    ];
};

export const addBoardService = (newBoard: IBoardModification): IBoard => {
    return {id: Date.now(), name: newBoard.name, description: newBoard.description, createdAt: new Date(), updatedAt: new Date()};
}

export const deleteBoardService = (id: number): void => {

};

