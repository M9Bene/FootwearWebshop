import './DetailedShoeView.css';
import {useEffect, useState} from "react";
import SizeAndQuantity from "./SizeAndQuantity";

function DetailedShoeView({shoeId}) {

    const [shoeData, setShoeData] = useState([]);

    useEffect(() => {
        fetch(
            "http://localhost:8080/api/detailed-shoe-info/shoe-id/" + shoeId
        )
            .then((response) => {
                if (response.ok) {
                    return response.json();
                }
                throw response;
            })
            .then((data) => {
                console.log(data);
                setShoeData(data);
            })
    }, [shoeId])


    return (
        <div className={"detailed-shoe-container"}>
            <div className={"image-container"}>
                <img className={"big-shoe-img"} src={shoeData.imgUrl} alt={"shoe"}/>
            </div>
            <div className={"info-container"}>
                <div className={"shoe-info"}>{shoeData.name}</div>
                <div className={"shoe-info"}>{shoeData.brand}</div>
                <div className={"shoe-info"}>{shoeData.price} $</div>
                <div className={"shoe-info"}>Available sizes:</div>
                <SizeAndQuantity shoeData={shoeData}/>
                <div className={"shoe-info"}>Information</div>
                <div className={"detailed-shoe-info"}>{shoeData.detailedInfo}</div>
            </div>
        </div>
    )
}

export default DetailedShoeView;
