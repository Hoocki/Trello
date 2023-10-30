import React, {useEffect, useState} from 'react';
import {Grid, List, ListItem, Typography} from "@mui/material";
import AddBoard from "./addBoard/AddBoard";
import Board from "./board/Board"
import {IBoard, IBoardModification} from "../../../models/Board";
import {BoardPanelProps} from "./props";
import "./styles.css";
import {addBoardService, deleteBoardService, getBoardsService} from "../../../services/board";

const BoardPanel: React.FC<BoardPanelProps> = ({selectedBoardId, updateSelectedBoardId}: BoardPanelProps) => {
    const [boards, setBoards] = useState<IBoard[]>([]);

    useEffect(() => {
        getBoards();
    }, [])

    const getBoards = () => {
        const fetchedBoards = getBoardsService();
        setBoards(fetchedBoards);
    }

    const addBoard = (newBoard: IBoardModification) => {
        const addedBoard = addBoardService(newBoard);
        setBoards([...boards, addedBoard])
    }

    const deleteBoard = (id: number) => {
        deleteBoardService(id);
        setBoards(boards.filter(board => board.id !== id));
        resetSelectedBoardId(id);
    }

    const resetSelectedBoardId = (id: number) => {
        if (selectedBoardId === id) {
            updateSelectedBoardId(-1);
        }
    }

    return (
        <Grid className="boardPanelContainer" container spacing={0} item xs={4}>
            <Grid item xs={12}>
                <AddBoard addBoard={addBoard}/>
            </Grid>
            <Grid className="listBoards" item xs={12}>
                <Typography className="typography" variant="h4">Boards</Typography>
                <List>
                    {boards.map((board) => (
                        <ListItem key={board.id}>
                            <Board board={board} deleteBoard={deleteBoard} updateSelectedBoardId={() => {
                                updateSelectedBoardId(board.id)
                            }}/>
                        </ListItem>
                    ))}
                </List>
            </Grid>
        </Grid>
    );
};

export default BoardPanel;