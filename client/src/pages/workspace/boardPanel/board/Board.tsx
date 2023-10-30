import React from 'react';
import {Card, CardContent, Typography, Button, Grid} from '@mui/material';
import {BoardProp} from './props';
import './styles.css';

const Board: React.FC<BoardProp> = ({board, deleteBoard, updateSelectedBoardId}: BoardProp) => {
    return (
        <Card className="board" variant="outlined">
            <CardContent>
                <Typography variant="h6">{board.name}</Typography>
                <Typography variant="body2">ID: {board.id}</Typography>
                <Typography variant="body2">Description: {board.description}</Typography>
                <Grid container spacing={1}>
                    <Grid item xs={6}>
                        <Button variant="contained" color="info" onClick={() => updateSelectedBoardId(board.id)}>
                            Show cards
                        </Button>
                    </Grid>
                    <Grid item xs={6} className="buttonDelete">
                        <Button variant="contained" color="error" onClick={() => deleteBoard(board.id)}>
                            Delete
                        </Button>
                    </Grid>
                </Grid>
            </CardContent>
        </Card>
    );
};

export default Board;
