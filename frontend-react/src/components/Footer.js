import './Footer.css';

function Footer(){

    return(
        <div className={"footer"}>
            <div className={"section"}>
                <p className={"section-title"}>Information</p>
                <p className={"section-item clickable"}>help</p>
                <p className={"section-item clickable"}>guarantee</p>
                <p className={"section-item clickable"}>shipping</p>
            </div>
            <div className={"section"}>
                <p className={"section-title"}>Contact</p>
                <p className={"section-item"}>Phone: +36 95 95 95 95</p>
                <p className={"section-item"}>e-mail: asd@marciStore.com</p>
                <p className={"section-item"}>office: Bp java district React street 01</p>
            </div>
            <div className={"section"}>
                <p className={"section-title"}>Shops</p>
                <p className={"section-item clickable"}>Bp - Arena</p>
                <p className={"section-item clickable"}>Bp - Westend</p>
            </div>
            <div className={"section"}>
                <p className={"section-title"}>Follow us</p>
                <p className={"section-item clickable"}>Facebook</p>
                <p className={"section-item clickable"}>Instagram</p>
                <p className={"section-item clickable"}>Twitter</p>
            </div>
        </div>
    );
}

export default Footer;