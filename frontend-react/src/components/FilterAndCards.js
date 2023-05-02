import ShoeCard from "./ShoeCard";
import './FilterAndCards.css';
import {useEffect, useState} from "react";
import Filter from "./Filter";

// constant options for filters
const brandOptions = ["all", "adidas", "nike"];
const sizeOptions = ["all", 38, 39, 40, 41, 42, 43, 44, 45, 46, 47];
const priceOrderOptions = ["no order", "ascending", "descending"];


function FilterAndCards({selectShoe}) {
    const [filterSettings, setFilterSettings] = useState({
        brand: "all", size: "all", priceOrder: "no order",
        minPrice: 0.0, maxPrice: 400.0
    })
    const [shoes, setShoes] = useState([]);
    const [openFilterModal, setOpenFilterModal] = useState(false);

    // fetch function with changing url according to the filter settings
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

            {/* Filters on the left side of the screen at wide screen */}
            {!openFilterModal &&
                <div className={"filter-container"}>
                    <Filter title={"brand"} options={brandOptions} setFilter={handleFilter}
                            selectedOption={filterSettings.brand}/>
                    <Filter title={"size"} options={sizeOptions} setFilter={handleFilter}
                            selectedOption={filterSettings.size}/>
                    <Filter title={"price order"} options={priceOrderOptions} setFilter={handleFilter}
                            selectedOption={filterSettings.priceOrder}/>
                    <Filter title={"price range"} setFilter={handleFilter} isRangeOption={true}
                            selectedMinValue={filterSettings.minPrice} selectedMaxValue={filterSettings.maxPrice} />
                </div>}

            {/* middle+right part for: cards, filter setting info and filter btn at small width screen */}
            <div className={"cards-and-filter-info"}>

                {/*  btn for filter modal at small width screen */}
                <div className={"filter-option-btn"}
                     onClick={() => setOpenFilterModal(!openFilterModal)}> Filter Options
                </div>

                {/*   filters in modal at small width screen when above btn is clicked */}
                {openFilterModal &&
                    <div className={"filter-modal"}>
                        <div className={"modal-filter-container"}>
                            <Filter title={"brand"} options={brandOptions} setFilter={handleFilter}
                                    selectedOption={filterSettings.brand}/>
                            <Filter title={"size"} options={sizeOptions} setFilter={handleFilter}
                                    selectedOption={filterSettings.size}/>
                            <Filter title={"price order"} options={priceOrderOptions} setFilter={handleFilter}
                                    selectedOption={filterSettings.priceOrder}/>
                            <Filter title={"price range"} setFilter={handleFilter} isRangeOption={true}
                                    selectedMinValue={filterSettings.minPrice} selectedMaxValue={filterSettings.maxPrice}/>
                        </div>
                        <div className={"overlay"} onClick={() => setOpenFilterModal(false)}></div>
                    </div>}

                {/*  information about selected filters */}
                <div className={"filter-info"}>
                    <span className={"colored"}>Filters used: </span>

                    {filterSettings.brand !== "all" &&
                        <span>  / /   brand: <span className={"colored"}>{filterSettings.brand}</span> </span>}

                    {filterSettings.size !== "all" &&
                        <span>  / /   size: <span className={"colored"}>{filterSettings.size}</span></span>}

                    {filterSettings.priceOrder !== "no order" &&
                        <span>  / /   price-order: <span
                            className={"colored"}>{filterSettings.priceOrder}</span></span>}

                    {Boolean(filterSettings.minPrice !== 0.0 | filterSettings.maxPrice !== 400.0) &&
                        <span>  / /   price-range: <span className={"colored"}>custom</span></span>}
                </div>

                {/*  cards for the products fetched from API */}
                <div className={"card-container"}>

                    {shoes.map((shoe, index) => {
                        return (
                            <ShoeCard key={index} shoeData={shoe} selectShoe={selectShoe}/>
                        )
                    })}
                </div>
            </div>
        </div>
    )
}

export default FilterAndCards;