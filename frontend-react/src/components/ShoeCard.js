import './ShoeCard.css'

function ShoeCard() {

    return (
        <div className={"card"}>
            <img className={"shoe-image"} src="https://i.imgur.com/IYHTrLJ.png" alt={"shoe"}/>
            <p className={"shoe-name"}>Yeezy Boost 350 V2 Jade Ash</p>
            <p className={"shoe-info"}>adidas</p>
            <p className={"shoe-info"}>120.0 $</p>
            <button>Detailed Info</button>
        </div>
    )
}

export default ShoeCard;
