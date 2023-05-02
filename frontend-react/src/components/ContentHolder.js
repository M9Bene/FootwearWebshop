import {useState} from "react";
import FilterAndCards from "./FilterAndCards";
import DetailedShoeView from "./DetailedShoeView";
import AboutPage from "./AboutPage";
import HelpPage from "./HelpPage";


function ContentHolder({content, setContent}) {

    const [selectedShoeId, setSelectedShoeId] = useState(null);

    function selectShoe(id) {
        setContent("detailedView");
        setSelectedShoeId(id);
    }

    return (
        <div>
            {content === "filterAndCards" ? <FilterAndCards selectShoe={selectShoe}/>
                : content === "detailedView" ? <DetailedShoeView shoeId={selectedShoeId} setContent={setContent}/>
                    : content === "aboutPage" ? <AboutPage/>
                        : content === "helpPage" ? <HelpPage/>
                            : <div>No content for this yet</div>}
        </div>
    )
}

export default ContentHolder;