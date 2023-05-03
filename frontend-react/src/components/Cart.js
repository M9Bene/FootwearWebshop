import {useContext, useEffect, useState} from "react";
import {CartContext} from "../cart_storage/CartContext";
import CartItem from "./CartItem";
import './Cart.css';


function Cart() {

    const {getAllCartItems} = useContext(CartContext);

    const [cartItems, setCartItems] = useState(getAllCartItems)

    useEffect(
        () => {
            setCartItems(getAllCartItems);
        }
    , [getAllCartItems]);

    return (
        <div className={"cart-container"}>
            <div className={"cart-title"}>Your Bag!</div>

            <div className={"cart-item-container"}>
                {cartItems.map((product, i) => (
                    <CartItem key={i} product={product}/>
                ))}
            </div>
        </div>
    )
}

export default Cart;