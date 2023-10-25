export interface IBoard {
    id: number
    name: string
    description: string
    createdAt: Date
    updatedAt: Date
}

export interface IBoardModification {
    name: string
    description: string
}