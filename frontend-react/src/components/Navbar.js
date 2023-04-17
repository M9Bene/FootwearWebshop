import './Navbar.css';

function Navbar() {

    return (
        <div className={"header"}>
            <div className="navbar">
                <div className={"logo"}>MARCI-STORE</div>
                <ul className={"menu-items"}>
                    <li>HOME</li>
                    <li>BRANDS</li>
                    <li>ABOUT</li>
                    <li>HELP</li>
                </ul>
                <div className={"active-icon-container"}>
                    <div className={"active-icon"}>cart</div>
                    <div className={"active-icon"}>login</div>
                </div>
                <div className={"menu-btn-container"}>

                </div>
            </div>
        </div>
    );
}

export default Navbar;