import { React, useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import BoardList from "../components/BoardList/BoardList";

const Main = () =>  {
    const [data, setData] = useState("")
    const handleInputBtnClick = (e) => {
        e.preventDefault();
    }

    useEffect(() => {
        const getBoardList = async () => {
            console.log("getBoardList()");
            let response = await axios.get("api/board.list");
            console.log("main/response: ", response);
            setData(response.data.data);
        };
        getBoardList();
    }, [])
    return (
        <>
        <Link to={"/create-board"}>
            <input type='button' value='게시글 작성하기' />
        </Link>
        <BoardList data={data}/>
        </>
    );
};
export default Main;