import ShoeCard from "./ShoeCard";
import './FilterAndCards.css';
import {useEffect, useState} from "react";
import Filter from "./Filter";


const brandOptions = ["all", "adidas", "nike"];
const sizeOptions = ["all", 38, 39, 40, 41, 42, 43, 44, 45, 46, 47];
const priceOrderOptions = ["no order", "ascending", "descending"];

function FilterAndCards() {

    const [filterSettings, setFilterSettings] = useState({
        brand: "all", size: "all", priceOrder: "no order",
        minPrice: 0.0, maxPrice: 400.0
    })

    const [shoes, setShoes] = useState([]);


    useEffect(() => {

        let additionForUrl = "";
        // if some filters selected  (their value is not equal to the default/all)
        // then we pass them to the url that we are going to fetch
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
            "http://localhost:8080/api/basic-shoe-info/p-range/"
            + filterSettings.minPrice + "/" + filterSettings.maxPrice + additionForUrl
        )
            .then((data) => {
                return data.json()
            })
            .then((data) => {
                setShoes(data);
            });
    }, [filterSettings])

    // function for the filter components to use and set the filter settings state
    const handleFilter = (filterName, filterOption, secondFilterOption) => {
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
            case "price range":
                setFilterSettings({
                    ...filterSettings,
                    minPrice: filterOption,
                    maxPrice: secondFilterOption
                })
                break;
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
                <Filter title={"price range"} setFilter={handleFilter} isRangeOption={true}/>

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