import {useState} from "react";
import './Filter.css';
import DoubleSlider from "./DoubleSlider";


function Filter({title, options, setFilter, isRangeOption, selectedOption, selectedMinValue, selectedMaxValue}) {

    const [showOptions, setShowOptions] = useState(false);

    if (isRangeOption) {
        // render Filter Component with a Range Slider as 'options'
        return (
            <div className={"filter"}>
                <div className={"filter-title"} onClick={() => setShowOptions(!showOptions)}>{title} selection</div>
                <div className={!showOptions ? "hide" : ""}>
                    <DoubleSlider min={0.0} max={400.0}  onChange={({min, max}) => {setFilter(title, min, max)}}
                                  selectedMinValue={selectedMinValue} selectedMaxValue={selectedMaxValue}/>
                </div>
            </div>
        )

    } else {
        // render Filter Component with divs as 'options'
        return (<div className={"filter"}>
            <div className={"filter-title"} onClick={() => setShowOptions(!showOptions)}>{title} selection</div>

            {options.map((option, i) => {
                return (<div key={i}
                             className={showOptions && selectedOption === option ? "selected filter-option"
                                 : showOptions ? "filter-option" : "hide"}
                             onClick={() => {
                                 setFilter(title, option);
                             }}>{option}</div>)
            })}
        </div>)
    }
}

export default Filter;