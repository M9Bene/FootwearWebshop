import './Navbar.css';
import { FaBars, FaCartPlus, FaUser, FaCross } from "react-icons/fa"


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
                    <FaCartPlus className={"active-icon"}/>
                    <FaUser className={"active-icon"}/>
                </div>
                <div className={"menu-btn"}>
                    <FaBars className={"active-icon"}/>
                </div>

            </div>
            <div className={"drop-down-menu"}>
                <ul className={"drop-down-menu-items"}>
                    <li>HOME</li>
                    <li>BRANDS</li>
                    <li>ABOUT</li>
                    <li>HELP</li>
                </ul>
            </div>
        </div>
    );
}

export default Navbar;