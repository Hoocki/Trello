import React, {useEffect, useState} from 'react';
import Board from "./board/Board";
import {IBoard, IBoardModification} from "../../models/Board";
import AddBoard from "./board/addBoard/AddBoard";
import {Container, List, ListItem, Paper, Typography} from "@mui/material";
import "./styles.css";
import {addBoardService, deleteBoardService, getBoardsService} from "../../services/board";

const Workspace: React.FC = () => {
    const [boards, setBoards] = useState<IBoard[]>([]);

    useEffect(() => {
        getBoards();
    }, [])

    const getBoards = () => {
        const fetchedBoards: IBoard[] = getBoardsService();
        setBoards(fetchedBoards);
    }

    const addBoard = (newBoard: IBoardModification) => {
        const addedBoard: IBoard = addBoardService(newBoard);
        setBoards([...boards, addedBoard])
    }

    const deleteBoard = (id: number) => {
        deleteBoardService(id);
        setBoards(boards.filter(board => board.id !== id))
    }

    return (
        <Container style={{marginLeft: '0', marginRight: 'auto'}} maxWidth="sm" className="container">
            <AddBoard onCreate={addBoard}/>
            <Paper className="paper">
                <Typography className="typography" variant="h4">Boards</Typography>
                <List>
                    {boards.map((board) => (
                        <ListItem key={board.id}>
                            <Board board={board} onDelete={deleteBoard}/>
                        </ListItem>
                    ))}
                </List>
            </Paper>
        </Container>
    );
};

export default Workspace;