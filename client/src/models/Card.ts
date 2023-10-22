export interface ICard {
    id: bigint
    name: string
    description: string
    createdAt: Date
    updatedAt: Date
}

export interface ICardModification {
    name: string
    description: string
}