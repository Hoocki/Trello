import React, {useState} from 'react';
import {IBoardModification} from "../../../../models/Board";
import {AddBoardProp} from "./props";
import Button from "@mui/material/Button";
import {Card, CardContent, TextField, Typography} from "@mui/material";
import "./styles.css";

const AddBoard: React.FC<AddBoardProp> = ({onCreate}: AddBoardProp) => {

    const [board, setBoard] = useState<IBoardModification>({name: '', description: ''})

    const addNewBoard = () => {
        onCreate(board);
        clearBoard();
    }

    const clearBoard = () => {
        setBoard({name: '', description: ''});
    }

    return (
        <Card variant="outlined">
            <CardContent>
                <Typography className={'typography'} variant="h4">New Board</Typography>
                <TextField
                    variant="outlined"
                    label="Name of board"
                    value={board.name}
                    onChange={({target: {value}}) => setBoard({...board, name: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <TextField
                    variant="outlined"
                    label="Description of board"
                    value={board.description}
                    onChange={({target: {value}}) => setBoard({...board, description: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <Button variant="contained" color="success" onClick={addNewBoard} fullWidth>
                    Add new board
                </Button>
            </CardContent>
        </Card>
    );
};

export default AddBoard;