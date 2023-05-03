import {CartContext} from "../cart_storage/CartContext";
import {useContext} from "react";
import './CartItem.css'

function CartItem({product}) {

    const {updateCart} = useContext(CartContext);

    return (
        <div className={"cart-item"}>
            <img className={"cart-shoe-img"} src={product.imgUrl} alt={"shoe"}/>
            <div className={"cart-item-info-container"}>
                <div className={"cart-item-info"}>{product.name}</div>
                <div className={"cart-item-info"}>size: {product.size}</div>
                <div className={"cart-item-info"}>quantity: {product.quantity}</div>
                <div className={"cart-item-info"}>price: {(product.quantity * product.price).toFixed(2)}$</div>
            </div>
            <div className={"quantity-modifier-container"}>

                <button className={"quantity-modifier"} onClick={() => {
                    updateCart(product, product.size, -1);
                }}> - quantity
                </button>
                <button className={"quantity-modifier"} onClick={() => {
                    updateCart(product, product.size, 1);
                }
                }> + quantity
                </button>

            </div>
        </div>
    )
}

export default CartItem;