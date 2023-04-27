import './App.css';
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import FilterAndCards from "./components/FilterAndCards";


function App() {



    return (
        <div className="App">
            <Navbar/>
            <FilterAndCards/>
            <Footer/>
        </div>
    );
}

export default App;
