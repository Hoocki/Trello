export interface ICard {
    id: number
    name: string
    description: string
    createdAt: Date
    updatedAt: Date
    boardId: number
}

export interface ICardModification {
    name: string
    description: string
}