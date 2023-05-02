import './ShoeCard.css'

function ShoeCard({shoeData, selectShoe}) {

    return (
        <div className={"card"}>
            <img className={"shoe-image"} src={shoeData.imgUrl} alt={"shoe"}/>
            <p className={"shoe-name"}>{shoeData.name}</p>
            <p className={"shoe-card-info"}>{shoeData.brand}</p>
            <p className={"shoe-card-info"}>{shoeData.price}</p>
            <button onClick={() => selectShoe(shoeData.id)} >Detailed Info</button>
        </div>
    )
}

export default ShoeCard;
