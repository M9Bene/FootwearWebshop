import ShoeCard from "./ShoeCard";
import './FilterAndCards.css';
import {useEffect, useState} from "react";
import Filter from "./Filter";


const brandOptions = ["all", "adidas", "nike"];
const sizeOptions = ["all", 38, 39, 40, 41, 42, 43, 44, 45, 46, 47];
const priceOrderOptions = ["no order", "ascending", "descending"];

function FilterAndCards() {

    const [filterSettings, setFilterSettings] = useState({brand: "all", size: "all", priceOrder: "no order"})

    const [shoes, setShoes] = useState([]);


    useEffect(() => {

            let additionForUrl = "";
            // if some filters selected  (their value is not equal to the default/all)
            // then we pass them to the url we gonna fetch
            if (filterSettings.size !== "all") {
                additionForUrl += "/by-size/" + filterSettings.size;
            }
            if (filterSettings.brand !== "all") {
                additionForUrl += "/by/" + filterSettings.brand;
            }
            if (filterSettings.priceOrder === "ascending") {
                additionForUrl += "/p-order/asc";
            } else if (filterSettings.priceOrder === "descending") {
                additionForUrl += "/p-order/desc";
            }

            fetch(
                "http://localhost:8080/api/basic-shoe-info/p-range/0.0/200.0" + additionForUrl
            )
                .then((data) => {
                    return data.json()
                })
                .then((data) => {
                    setShoes(data);
                });
        }
        ,
        [filterSettings]
    )

    // function for the filter components to use and set the filter settings state
    const handleFilter = (filterName, filterOption) => {
        switch (filterName) {
            case "brand":
                setFilterSettings({
                    ...filterSettings,
                    brand: filterOption
                });
                break;
            case "size":
                setFilterSettings({
                    ...filterSettings,
                    size: filterOption
                });
                break;
            case "price order":
                setFilterSettings({
                    ...filterSettings,
                    priceOrder: filterOption
                });
                break;
            // todo add price range
            default :
                break;
        }
    }

    return (
        <div className={"middle-section"}>
            <div className={"filter-container"}>

                <Filter title={"brand"} options={brandOptions} setFilter={handleFilter}/>
                <Filter title={"size"} options={sizeOptions} setFilter={handleFilter}/>
                <Filter title={"price order"} options={priceOrderOptions} setFilter={handleFilter}/>

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