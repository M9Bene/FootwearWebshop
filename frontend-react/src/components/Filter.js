import {useState} from "react";
import './Filter.css';


function Filter({title, options, setFilter}) {

    const [showOptions, setShowOptions] = useState(false);
    const [selected, setSelected] = useState("all");


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

export default Filter;