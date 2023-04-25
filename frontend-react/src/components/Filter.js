import {useState} from "react";
import './Filter.css';
import MultiRangeSlider from "./MultiRangeSlider";

function Filter({title, options, setFilter, isRangeOption}) {

    const [showOptions, setShowOptions] = useState(false);
    const [selected, setSelected] = useState("all");

    if (isRangeOption) {
        // render Filter Component with a Range Slider as 'options'
        return (
            <div className={"filter"}>
                <div className={"filter-title"} onClick={() => setShowOptions(!showOptions)}>{title} Selection</div>

                <div className={!showOptions && "hide"}>
                    <MultiRangeSlider min={0.0} max={400.0}
                                      onChange={({min, max}) => {setFilter(title, min, max)}}
                    />
                </div>
            </div>
        )

    } else {
        // render Filter Component with divs as 'options'
        return (<div className={"filter"}>
            <div className={"filter-title"} onClick={() => setShowOptions(!showOptions)}>{title} Selection</div>

            {options.map((option, i) => {
                return (<div key={i}
                             className={showOptions && selected === option ? "selected filter-option"
                                 : showOptions ? "filter-option" : "hide"}
                             onClick={() => {
                                 setFilter(title, option);
                                 setSelected(option);
                             }}>{option}</div>)
            })}
        </div>)
    }
}

export default Filter;