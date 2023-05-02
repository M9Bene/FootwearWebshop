import './SizeAndQuantity.css'
import {useContext, useState} from "react";
import {CartContext} from "../cart_storage/CartContext";


function SizeAndQuantity({shoeData}) {

    const [selected, setSelected] = useState(null);

    const {updateCart} = useContext(CartContext);

    if (!shoeData.sizeAndQuantityList) {
        return (
            <div> No Available size for this Shoe</div>
        )
    }

    const handlePurchase = () => {
        if(selected === null){
            alert("Select a Size!");
        } else {
            updateCart(shoeData , shoeData.sizeAndQuantityList[selected].size, 1)
        }
    }

    return (
        <div className={"size-and-quantity-container"}>
            <div className={"size-container"}>
                {shoeData.sizeAndQuantityList.map((shoe, i) => {
                    return (
                        <div key={i} className={i === selected ? "selected size" : "size"}
                             onClick={() => {
                                 setSelected(i);
                             }}>{shoe.size}</div>)
                })}
            </div>
            <div className={"quantity-info"}>
                {selected === null ? <div>( Select a size! )</div>
                    : shoeData.sizeAndQuantityList[selected].quantity > 1 ? <div>( In stock )</div>
                        : <div>( Last product for this size )</div>}
            </div>
            <div className={"purchase-btn"} onClick={handlePurchase }>Add to cart!</div>
        </div>
    )
}

export default SizeAndQuantity;