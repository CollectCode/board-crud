import React from "react";
import { Link } from "react-router-dom";

const BoardBox = (props) => {
    console.log("boardBox/props: ", props);
    console.log("boardBox/props.title: ", props.title);
    return(
        <>
            <Link
                to = {"/detail"}
                state = {{
                    id: props.id,
                }}
            >
                <div>
                    <h1>{props.title}</h1>
                </div>

            </Link>
        </>
    )
}
export default BoardBox;