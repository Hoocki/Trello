import React, {useEffect, useState} from 'react';
import Button from "@mui/material/Button";
import {Card, CardContent, TextField, Typography} from "@mui/material";
import {AddCardProp} from './props';
import {ICardModification} from "../../../../models/Card";
import "./styles.css";

const AddCard: React.FC<AddCardProp> = ({selectedBoardId, addCard}: AddCardProp) => {
    const [card, setCard] = useState<ICardModification>({name: '', description: ''})
    const [isValid, setIsValid] = useState<boolean>(false)

    useEffect(() => {
        validateCard(card);
    }, [card, selectedBoardId]);

    const validateCard = (card: ICardModification) => {
        if (selectedBoardId >= 0 && card.name !== "" && card.description !== "") {
            setIsValid(true)
        } else {
            setIsValid(false)
        }
    }

    const addNewCard = () => {
        addCard(card);
        clearCard();
    }

    const clearCard = () => {
        setCard({name: '', description: ''});
    }

    return (
        <Card variant="outlined" className="addCard">
            <CardContent>
                <Typography className="typography" variant="h4">New Card</Typography>
                <TextField
                    variant="outlined"
                    label="Name of card"
                    value={card.name}
                    onChange={({target: {value}}) => setCard({...card, name: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <TextField
                    variant="outlined"
                    label="Description of card"
                    value={card.description}
                    onChange={({target: {value}}) => setCard({...card, description: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <Button variant="contained" color="success" onClick={addNewCard} disabled={!isValid} fullWidth>
                    Add new card
                </Button>
            </CardContent>
        </Card>
    );
};

export default AddCard;