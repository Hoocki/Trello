import {IBoard, IBoardModification} from "../models/Board";
import _ from "lodash";
import httpClient from "./httpClient";

const URL = "boards";

export const getBoardsService = async ():Promise<IBoard[]> => {
    const response = await httpClient.get<IBoard[]>(URL);
    return _.get(response, "data", []);
}

export const addBoardService = async (newBoard: IBoardModification): Promise<IBoard | null> => {
    const response = await httpClient.post<IBoard>(URL, newBoard);
    return _.get(response, "data", null);
}

export const updateBoardService = async (id: number, updateBoard: IBoardModification): Promise<IBoard | null> => {
    const response = await httpClient.put<IBoard>(`${URL}/${id}`, updateBoard);
    return _.get(response, "data", null);
}

export const deleteBoardService = async (id: number): Promise<void> => {
    await httpClient.delete(`${URL}/${id}`);
}