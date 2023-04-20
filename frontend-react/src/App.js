import './App.css';
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import ShoeCard from "./components/ShoeCard";


function App() {

    return (
        <div className="App">
            <Navbar/>
            <div className={"card-container"}>
                <ShoeCard />
                <ShoeCard />
                <ShoeCard />
                <ShoeCard />
                <ShoeCard />
            </div>
            <Footer/>
        </div>
    );
}

export default App;
