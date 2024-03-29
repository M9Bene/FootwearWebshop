import {createContext, useEffect, useState} from "react";


export const CartContext = createContext(null);

export const CartContextProvider = (props) => {

    const loadedCartItems = JSON.parse(localStorage.getItem("cart")) ;
    // if localstorage has data for cart then use that data for state initialization otherwise empty array
    const [cartItems, setCartItems] = useState( loadedCartItems != null ? loadedCartItems : []);


    function updateCart(shoeData, selectedSize, quantityChange){

        let updatedCart = [];
        let isUpdated = false;

        for (const cartItem of cartItems) {
            // if new product is already in cart ( checked by id and size) in same size
            // set isUpdated to true
            if (cartItem.id === shoeData.id && cartItem.size === selectedSize){
                // if its changed quantity above zero then update its quantity otherwise skip copying it = Remove it
                if(cartItem.quantity + quantityChange > 0 ) {
                    updatedCart = [...updatedCart,
                        {
                            id: shoeData.id,
                            name: shoeData.name,
                            price: shoeData.price,
                            imgUrl: shoeData.imgUrl,
                            size: selectedSize,
                            quantity: cartItem.quantity + quantityChange
                        }]
                }
                isUpdated = true;
            } else {
                // copy already existing cartItems
                updatedCart = [...updatedCart, cartItem];
            }
        }
        // if isUpdated is false that means the new CartItem isn't in the cart yet (in the same size )
        // so it's need to be added with a starter quantity = 1
        if(!isUpdated){
            updatedCart = [...updatedCart,
                {
                    id: shoeData.id,
                    name: shoeData.name,
                    price: shoeData.price,
                    imgUrl: shoeData.imgUrl,
                    size: selectedSize,
                    quantity: 1
                }];
        }
        setCartItems(updatedCart);
    }

    // save cart items (state) into local storage when it's data changed
    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cartItems));
    }, [cartItems]);

    function getAllCartItems() {
        return cartItems;
    }

    const contextValue = {updateCart, getAllCartItems};

    return(
        <CartContext.Provider value={contextValue}>
            {props.children}
        </CartContext.Provider>
    )
}
