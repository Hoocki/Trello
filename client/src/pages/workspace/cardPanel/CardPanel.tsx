import React, {useEffect, useState} from 'react';
import {Grid, List, ListItem, Typography} from "@mui/material";
import AddCard from "./addCard/AddCard";
import CardComponent from "./card/CardComponent";
import {CardPanelProps} from "./props";
import {ICard, ICardModification} from "../../../models/Card";
import "./styles.css";
import {addCardService, deleteCardService, getCardsByBoardIdService, updateCardService} from "../../../services/card";

const CardPanel: React.FC<CardPanelProps> = ({selectedBoardId}: CardPanelProps) => {
    const [cards, setCards] = useState<ICard[]>([]);

    useEffect(() => {
        getCards().then();
    }, [selectedBoardId])

    const getCards = async () => {
        const fetchedCards = await getCardsByBoardIdService(selectedBoardId);
        setCards(fetchedCards);
    }

    const addCard = async (newCard: ICardModification) => {
        const addedCard = await addCardService(selectedBoardId, newCard);
        if (addedCard !== null) {
            setCards([...cards, addedCard]);
        }
    }

    const updateCard = async (id: number, updateCard: ICardModification) => {
        const updatedBoard = await updateCardService(selectedBoardId, id, updateCard);
        if (updatedBoard !== null) {
            await getCards();
        }
    }

    const deleteCard = async (cardId: number) => {
        await deleteCardService(selectedBoardId, cardId);
        await getCards();
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
                                    <CardComponent card={card} deleteCard={deleteCard} updateCard={updateCard}/>
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