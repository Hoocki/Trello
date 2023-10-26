import React, {useState} from 'react';
import {Grid} from "@mui/material";
import BoardPanel from "./boardPanel/BoardPanel";
import CardPanel from "./cardPanel/CardPanel";

const Workspace: React.FC = () => {
    const [selectedBoardId, setSelectedBoardId] = useState<number>(-1);

    return (
        <Grid container spacing={0}>
            <BoardPanel selectedBoardId={selectedBoardId} updateSelectedBoardId={setSelectedBoardId}/>
            <CardPanel selectedBoardId={selectedBoardId}/>
        </Grid>
    );
};

export default Workspace;