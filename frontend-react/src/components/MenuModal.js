import './MenuModal.css';

function MenuModal({open, close}) {

    if (!open) return null;

    return (
        <div className={"menu-modal"}>
            <div className={"drop-down-menu"}>
                <ul className={"drop-down-menu-items"}>
                    <div onClick={close} className={"close-icon"}>X</div>
                    <li>HOME</li>
                    <li>BRANDS</li>
                    <li>ABOUT</li>
                    <li>HELP</li>
                    <li>CART</li>
                    <li>LOGIN</li>
                </ul>
            </div>
            <div onClick={close} className={"overlay"}></div>
        </div>
    );
}

export default MenuModal;