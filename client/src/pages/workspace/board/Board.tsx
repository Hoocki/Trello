import React from 'react';
import {Card, CardContent, Typography, Button} from '@mui/material';
import {DeleteBoardProp} from "./props";

const Board: React.FC<DeleteBoardProp> = ({board, onDelete}: DeleteBoardProp) => {
    return (
        <Card variant="outlined">
            <CardContent>
                <Typography variant="h6">{board.name}</Typography>
                <Typography variant="body2">ID: {board.id}</Typography>
                <Typography variant="body2">Description: {board.description}</Typography>
                <Button variant="contained" color="error" onClick={() => onDelete(board.id)}>
                    Delete
                </Button>
            </CardContent>
        </Card>
    );
};

export default Board;