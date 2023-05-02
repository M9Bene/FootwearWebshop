import './App.css';
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import ContentHolder from "./components/ContentHolder";
import {memo, useState} from "react";
import {CartContextProvider} from "./cart_storage/CartContext";

function App() {


    const [content, setContent] = useState("filterAndCards")

    return (
        <div className="App">
            <MemoizedNavbar setContent={setContent}/>

            <CartContextProvider>

            <ContentHolder content={content} setContent={setContent}/>

            </CartContextProvider>

            <MemoizedFooter/>
        </div>
    );
}

const MemoizedNavbar = memo(Navbar);
const MemoizedFooter = memo(Footer);

export default App;
