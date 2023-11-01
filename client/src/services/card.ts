import {ICard, ICardModification} from "../models/Card";
import httpClient from "./httpClient";
import _ from "lodash";

const URL = "boards";

export const getCardsByBoardIdService = async (boardId: number): Promise<ICard[]> => {
    const response = await httpClient.get<ICard[]>(`${URL}/${boardId}/cards`);
    return _.get(response, "data", []);
}

export const addCardService = async (boardId: number, newCard: ICardModification): Promise<ICard | null> => {
    const response = await httpClient.post<ICard>(`${URL}/${boardId}/cards`, newCard);
    return _.get(response, "data", null);
}

export const updateCardService = async (boardId: number, id: number, updateCard: ICardModification): Promise<ICard | null> => {
    const response = await httpClient.put<ICard>(`${URL}/${boardId}/cards/${id}`, updateCard);
    return _.get(response, "data", null);
}

export const deleteCardService = async (boardId: number, id: number): Promise<void> => {
    await httpClient.delete(`${URL}/${boardId}/cards/${id}`);
}
