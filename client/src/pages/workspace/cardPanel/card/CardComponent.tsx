import React from 'react';
import {Card, CardContent, Typography, Button} from '@mui/material';
import {CardProp} from "./props";
import "./styles.css";

const CardComponent: React.FC<CardProp> = ({card, deleteCard}: CardProp) => {
    return (
        <Card className="card" variant="outlined">
            <CardContent>
                <Typography variant="h6">{card.name}</Typography>
                <Typography variant="body2">ID: {card.id}</Typography>
                <Typography variant="body2">Description: {card.description}</Typography>
                <Typography variant="body2">Date: {card.createdAt.toLocaleString()}</Typography>
                <Button variant="contained" color="error" onClick={() => deleteCard(card.id)}>
                    Delete
                </Button>
            </CardContent>
        </Card>
    );
};

export default CardComponent;