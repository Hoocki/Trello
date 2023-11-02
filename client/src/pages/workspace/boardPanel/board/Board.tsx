import React, {useState} from 'react';
import {Card, CardContent, Typography, Button, Grid, TextField} from '@mui/material';
import {BoardProp} from './props';
import './styles.css';
import {IBoardModification} from "../../../../models/Board";

const Board: React.FC<BoardProp> = ({board, deleteBoard, updateBoard, updateSelectedBoardId}: BoardProp) => {
    const [modifiedBoard, setModifiedBoard] =
        useState<IBoardModification>({name: board.name, description: board.description});

    return (
        <Card className="board" variant="outlined">
            <CardContent>
                <Typography variant="body1" aria-readonly={"true"}>ID: {board.id}</Typography>
                <TextField
                    variant="outlined"
                    label="Name"
                    value={modifiedBoard.name}
                    onChange={({target: {value}}) => setModifiedBoard({...modifiedBoard, name: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <TextField
                    variant="outlined"
                    label="Description"
                    value={modifiedBoard.description}
                    onChange={({target: {value}}) => setModifiedBoard({...modifiedBoard, description: value})}
                    type="text"
                    fullWidth
                    margin="normal"
                />
                <Grid container spacing={1} justifyContent="center">
                    <Grid item xs={4}>
                        <Button variant="contained" color="info" onClick={() => updateSelectedBoardId(board.id)}>
                            Show cards
                        </Button>
                    </Grid>
                    <Grid item xs={4}>
                        <Button variant="contained" color="primary"
                                onClick={() => updateBoard(board.id, modifiedBoard)}>
                            Update board
                        </Button>
                    </Grid>
                    <Grid item xs={4} className="buttonDelete">
                        <Button variant="contained" color="error" onClick={() => deleteBoard(board.id)}>
                            Delete board
                        </Button>
                    </Grid>
                </Grid>
            </CardContent>
        </Card>
    );
};

export default Board;