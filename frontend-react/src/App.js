import './App.css';
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import ContentHolder from "./components/ContentHolder";
import { useState} from "react";


function App() {

    const [content, setContent] = useState("filterAndCards")

    return (
        <div className="App">
            <Navbar setContent={setContent}/>
            <ContentHolder content={content} setContent={setContent}/>
            <Footer/>
        </div>
    );
}


export default App;
