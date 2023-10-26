import React, {useEffect, useState} from 'react';
import {Grid, List, ListItem, Typography} from "@mui/material";
import AddCard from "./addCard/AddCard";
import CardComponent from "./card/CardComponent";
import {CardPanelProps} from "./props";
import {ICard, ICardModification} from "../../../models/Card";
import "./styles.css";
import {addCardService, deleteCardService, getCardsByBoardIdService} from "../../../services/card";

const CardPanel: React.FC<CardPanelProps> = ({selectedBoardId}: CardPanelProps) => {
    const [cards, setCards] = useState<ICard[]>([]);

    useEffect(() => {
        getCards(selectedBoardId);
    }, [selectedBoardId])

    const getCards = (boardId: number) => {
        const fetchedCards = getCardsByBoardIdService(boardId);
        setCards(fetchedCards.filter(card => card.boardId === boardId));
    }

    const addCard = (boardId: number, newCard: ICardModification) => {
        const addedCard = addCardService(boardId, newCard);
        setCards([...cards, addedCard].filter(card => card.boardId === boardId))
    }

    const deleteCard = (cardId: number) => {
        deleteCardService(cardId);
        setCards(cards.filter(card => card.id !== cardId))
    }

    return (
        <Grid className="cardPanelContainer" item xs={8}>
            <Grid item xs={12}>
                <AddCard selectedBoardId={selectedBoardId} addCard={addCard}/>
            </Grid>
            <Grid className="listCards" item xs={12}>
                <Typography className="typography" variant="h4">Cards</Typography>
                <List>
                    <Grid container spacing={2}>
                        {cards.map((card) => (
                            <Grid key={card.id} item xs={4}>
                                <ListItem>
                                    <CardComponent card={card} deleteCard={deleteCard}/>
                                </ListItem>
                            </Grid>
                        ))}
                    </Grid>
                </List>
            </Grid>
        </Grid>
    );
};

export default CardPanel;