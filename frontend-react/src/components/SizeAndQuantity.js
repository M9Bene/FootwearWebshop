import './SizeAndQuantity.css'
import {useState} from "react";


function SizeAndQuantity({data}) {

    const [selected, setSelected] = useState(null);

    if (!data) {
        return (
            <div> No Available size for this Shoe</div>
        )
    }


    return (
        <div className={"size-and-quantity-container"}>
            <div className={"size-container"}>
                {data.map((shoe, i) => {
                    return (
                        <div key={i} className={i === selected ? "selected size" : "size"}
                             onClick={() => {
                                 setSelected(i);
                             }}>{shoe.size}</div>)
                })}
            </div>
            <div className={"quantity-info"}>
                {selected === null ? <div>( Select a size! )</div>
                    : data[selected].quantity > 1 ? <div>( In stock )</div>
                        : <div>( Last product for this size )</div>}
            </div>
            <div className={"purchase-btn"}>Add to cart!</div>
        </div>
    )
}

export default SizeAndQuantity;