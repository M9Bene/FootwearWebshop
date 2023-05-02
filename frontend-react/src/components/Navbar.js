import './Navbar.css';
import {useState} from "react";
import MenuModal from "./MenuModal";


function Navbar({setContent}) {

    const [openMenuModal, setOpenMenuModal] = useState(false);

    return (
        <div className={"header"}>
            <div className="navbar">
                <div className={"logo"}>MARCI-STORE</div>
                <ul className={"menu-items"}>
                    <li onClick={() => {setContent("filterAndCards")}}>HOME</li>
                    <li onClick={() => {setContent("")}}>BRANDS</li>
                    <li onClick={() => {setContent("aboutPage")}}>ABOUT</li>
                    <li onClick={() => {setContent("helpPage")}}>HELP</li>
                </ul>
                <div className={"active-icon-container"}>
                    <div className={"active-icon"}>cart</div>
                    <div className={"active-icon"}>login</div>
                </div>
                <div className={"menu-btn-container"}>
                    <div onClick={() => setOpenMenuModal(!openMenuModal)} className={"menu-btn"}>menu</div>
                </div>
            </div>
            <MenuModal open={openMenuModal} close={() => setOpenMenuModal(false)} setContent={setContent}/>
        </div>
    );
}

export default Navbar;