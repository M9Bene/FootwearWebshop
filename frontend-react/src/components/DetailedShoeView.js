import './DetailedShoeView.css';
import {useEffect, useState} from "react";

function DetailedShoeView({shoeId}) {

    const [shoeData, setShoeData] = useState([]);


    useEffect(() => {
        fetch(
            "http://localhost:8080/api/detailed-shoe-info/shoe-id/" + shoeId
        )
            .then((response) => {
                if(response.ok){
                    return response.json();
                }
                throw response;
            })
            .then((data) => {
                console.log(data);
                setShoeData(data);
            })
    },[shoeId])


    return (
        <div className={"detailed-shoe-container"}>

        </div>
    )
}

export default DetailedShoeView;
