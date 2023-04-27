import React, { useCallback, useEffect, useState, useRef } from "react";
import "./DoubleSlider.css";

const DoubleSlider = ({ min, max, onChange, selectedMinValue, selectedMaxValue }) => {
    const [minVal, setMinVal] = useState(selectedMinValue);
    const [maxVal, setMaxVal] = useState(selectedMaxValue);
    const minValRef = useRef(selectedMinValue);
    const maxValRef = useRef(selectedMaxValue);
    const range = useRef(null);

    // Convert to percentage
    const getPercent = useCallback(
        (value) => Math.round(((value - min) / (max - min)) * 100),
        [min, max]
    );

    // Set width of the range to decrease from the left side
    useEffect(() => {
        const minPercent = getPercent(minVal);
        const maxPercent = getPercent(maxValRef.current);

        if (range.current) {
            range.current.style.left = `${minPercent}%`;
            range.current.style.width = `${maxPercent - minPercent}%`;
        }
    }, [minVal, getPercent]);

    // Set width of the range to decrease from the right side
    useEffect(() => {
        const minPercent = getPercent(minValRef.current);
        const maxPercent = getPercent(maxVal);

        if (range.current) {
            range.current.style.width = `${maxPercent - minPercent}%`;
        }
    }, [maxVal, getPercent]);

    // Get min and max values when their state changes with Debouncing!
    useEffect(() => {
        const setValues =  setTimeout(() => {
            // Don't let call the onChange function to set state if none of the values changed
            // --> to prevent unnecessary re-rendering
            if ( minVal !== selectedMinValue || maxVal !== selectedMaxValue){
                onChange({ min: minVal, max: maxVal });
            }
            // debounce
        }, 1000)

        return () => clearTimeout(setValues)
    }, [minVal, maxVal]);

    return (
        <div className="container">
            <input
                type="range"
                min={min}
                max={max}
                value={minVal}
                onChange={(event) => {
                    const value = Math.min(Number(event.target.value), maxVal - 1);
                    setMinVal(value);
                    minValRef.current = value;
                }}
                className="thumb thumb--left"
            />
            <input
                type="range"
                min={min}
                max={max}
                value={maxVal}
                onChange={(event) => {
                    const value = Math.max(Number(event.target.value), minVal + 1);
                    setMaxVal(value);
                    maxValRef.current = value;
                }}
                className="thumb thumb--right"
            />

            <div className="slider">
                <div className="slider__track" />
                <div ref={range} className="slider__range" />
                <div className="slider__left-value">{minVal} $</div>
                <div className="slider__right-value">{maxVal} $</div>
            </div>
        </div>
    );
};

export default DoubleSlider;