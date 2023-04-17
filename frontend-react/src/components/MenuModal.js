import './MenuModal.css';

function MenuModal({open}) {

    if (!open) return null;

    return (
        <div className={"menu-modal"}>
            <div className={"drop-down-menu"}>
                <ul className={"drop-down-menu-items"}>
                    <li>HOME</li>
                    <li>BRANDS</li>
                    <li>ABOUT</li>
                    <li>HELP</li>
                    <li>CART</li>
                    <li>LOGIN</li>
                </ul>
            </div>
        </div>
    );
}

export default MenuModal;