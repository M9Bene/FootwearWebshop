import './MenuModal.css';

function MenuModal({open, close, setContent}) {

    if (!open) return null;

    return (
        <div className={"menu-modal"}>
            <div className={"drop-down-menu"}>
                <ul className={"drop-down-menu-items"}>
                    <div onClick={close} className={"close-icon"}>X</div>
                    <li onClick={() => {setContent("filterAndCards")}}>HOME</li>
                    <li onClick={() => {setContent("")}}>BRANDS</li>
                    <li onClick={() => {setContent("aboutPage")}}>ABOUT</li>
                    <li onClick={() => {setContent("helpPage")}}>HELP</li>
                    <li>CART</li>
                    <li>LOGIN</li>
                </ul>
            </div>
            <div onClick={close} className={"overlay"}></div>
        </div>
    );
}

export default MenuModal;