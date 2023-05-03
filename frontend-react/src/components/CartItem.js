import {CartContext} from "../cart_storage/CartContext";
import {useContext} from "react";
import './CartItem.css'

function CartItem({product}) {

    const {updateCart} = useContext(CartContext);

    return (
        <div className={"cart-item"}>
            <img className={"cart-shoe-img"} src={product.imgUrl} alt={"shoe"}/>
            <div className={"cart-item-info"}>
                <div>{product.name}</div>
                <div>size: {product.size}</div>
                <div>quantity: {product.quantity}</div>
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