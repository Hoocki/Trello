import React, {useEffect, useState} from 'react';
import {Card, CardContent, Typography, Button, TextField, Grid} from '@mui/material';
import {CardProp} from "./props";
import "./styles.css";
import {ICard, ICardModification} from "../../../../models/Card";

const CardComponent: React.FC<CardProp> = ({card, updateCard, deleteCard}: CardProp) => {
    const [modifiedCard, setModifiedCard] =
        useState<ICardModification>({name: card.name, description: card.description});

    const [date, setDate] = useState<string>("");

    useEffect(() => {
        formatDate(card)
    }, [card]);

    const formatDate = (card: ICard) => {
        const createdDate = new Date(card.createdAt);
        const year = createdDate.getFullYear().toString();
        const month = (createdDate.getMonth() + 1).toString().padStart(2, '0');
        const day = createdDate.getDate().toString().padStart(2, '0');
        const formattedDate = `${year}/${month}/${day}`;
        setDate(formattedDate);
    }

    return (
        <Card className="card" variant="outlined">
            <CardContent>
                <Typography variant="body1" aria-readonly={"true"}>ID: {card.id}</Typography>
                <TextField
                    variant="outlined"
                    label="Name"
                    value={modifiedCard.name}
                    onChange={({target: {value}}) => setModifiedCard({...modifiedCard, name: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <TextField
                    variant="outlined"
                    label="Description"
                    value={modifiedCard.description}
                    onChange={({target: {value}}) => setModifiedCard({...modifiedCard, description: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <Typography variant="body2">Date: {date}</Typography>
                <Grid container spacing={1}>
                    <Grid item xs={6}>
                        <Button variant="contained" color="primary"
                                onClick={() => updateCard(card.id, modifiedCard)}>
                            Update card
                        </Button>
                    </Grid>
                    <Grid item xs={6} className="buttonDelete">
                        <Button variant="contained" color="error" onClick={() => deleteCard(card.id)}>
                            Delete
                        </Button>
                    </Grid>
                </Grid>
            </CardContent>
        </Card>
    );
};

export default CardComponent;