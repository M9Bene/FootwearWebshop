import ShoeCard from "./ShoeCard";
import './FilterAndCards.css';
import {useEffect, useState} from "react";
import Filter from "./Filter";


const brandOptions = ["all", "adidas", "nike"];
const sizeOptions = ["all", 38, 39, 40, 41, 42, 43, 44, 45, 46, 47];
const priceOrderOptions = ["no order", "ascending", "descending"];

function FilterAndCards() {

    const [filterSettings, setFilterSettings] = useState({brand: "all", size: "all", priceOrder: "asc"})

    const [shoes, setShoes] = useState([]);


    useEffect(() => {
        fetch(
            "http://localhost:8080/api/basic-shoe-info/p-range/0.0/200.0"
        )
            .then((data) => {
                return data.json()
            })
            .then((data) => {
                console.log(data);
                setShoes(data);
            });
    }, [filterSettings])

    const handleFilterTest = (filterName, filterOption) => {
        // todo  change  filterSettings State here with switch case  ( setFilterSettings )
    }

    return (
        <div className={"middle-section"}>
            <div className={"filter-container"}>

                <Filter title={"brand"} options={brandOptions} setFilter={handleFilterTest}/>
                <Filter title={"size"} options={sizeOptions} setFilter={handleFilterTest}/>
                <Filter title={"price order"} options={priceOrderOptions} setFilter={handleFilterTest}/>

            </div>
            <div className={"card-container"}>

                {shoes.map((shoe, index) => {
                    return (
                        <ShoeCard key={index} shoeData={shoe}/>
                    )
                })}
            </div>
        </div>
    )
}

export default FilterAndCards;