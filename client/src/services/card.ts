import {ICard, ICardModification} from "../models/Card";

export const getCardsByBoardIdService = (boardId: number): ICard[] => {
    return [
        {id: 1, name: 'card1', description: 'cardDesc1', createdAt: new Date(), updatedAt: new Date(), boardId: 1},
        {id: 2, name: 'card2', description: 'cardDesc2', createdAt: new Date(), updatedAt: new Date(), boardId: 1},
        {id: 3, name: 'card3', description: 'cardDesc3', createdAt: new Date(), updatedAt: new Date(), boardId: 2},
    ];
};

export const addCardService = (boardId: number, newCard: ICardModification): ICard => {
    return {id: Date.now(), name: newCard.name, description: newCard.description, createdAt: new Date(), updatedAt: new Date(), boardId: boardId};
}

export const deleteCardService = (id: number): void => {

};
