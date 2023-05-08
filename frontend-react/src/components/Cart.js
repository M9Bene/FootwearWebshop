import {useContext, useEffect, useState} from "react";
import {CartContext} from "../cart_storage/CartContext";
import CartItem from "./CartItem";
import './Cart.css';


function Cart() {

    const {getAllCartItems} = useContext(CartContext);

    const [cartItems, setCartItems] = useState(getAllCartItems)
    const [totalPrice, setTotalPrice] = useState(0)
    const [isCartEmpty, setIsCartEmpty] = useState(true);

    useEffect(() =>{
        Object.keys(cartItems).length === 0 ? setIsCartEmpty(true) :setIsCartEmpty(false)
    }, [cartItems]);

    useEffect(
        () => {
            // function for count total Price according to products price and quantity
            const handleTotalPrice = () => {
                let totalPrice = 0;
                for (const cartItem of cartItems) {
                    totalPrice += cartItem.price * cartItem.quantity;
                }
                setTotalPrice(totalPrice);
            }
            // set updated cart items
            setCartItems(getAllCartItems);
            //  count total Price
            handleTotalPrice();
        }
    , [cartItems, getAllCartItems]);

    return (
        <div className={"cart-container"}>
            <div className={"cart-title"}>Your Bag!</div>
            <div className={"cart-info"}>Total cost: {totalPrice.toFixed(2)}</div>
            <div className={"cart-item-container"}>
                {cartItems.map((product, i) => (
                    <CartItem key={i} product={product}/>
                ))}
            </div>
            <div className={"cart-info"}>
                { isCartEmpty ? (<div>Your Bag is Empty</div>) : (<button className={"cart-purchase-btn"}>Purchase!</button>)}
            </div>

        </div>
    )
}

export default Cart;