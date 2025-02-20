import React, { useEffect, useState } from "react";
import "./ProductCard.css";
import { Button } from "@mui/material";
import { Favorite, ModeComment } from "@mui/icons-material";
import { teal } from "@mui/material/colors";

const images = [
    "https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_B.jpg",
    "https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_C.jpg",
    "https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_D.jpg",
    "https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_A.jpg"
]
const ProductCard = () => {
    const [currentImg, setCurrentImg] = useState(0);
    const [isHovered, setIsHovered] = useState(false);

    useEffect(() => {
        let interval: any
        if(isHovered) {
            interval = setInterval(() => {
                setCurrentImg((prevImg) => (prevImg+1)%images.length);
            }, 1000);
        } else if(interval){
            clearInterval(interval);
            interval = null;
        }
        return () => clearInterval(interval);
    }, [isHovered])

    return ( 
        <>
        <div className="group px-4 relative">
            <div className="card" onMouseEnter={() => setIsHovered(true)} onMouseLeave={() => setIsHovered(false)}>
                {images.map((item, index) => <img
                className="card-media object-top"
                src={item} alt="" style={{transform: `translateX(${(index-currentImg)*100}%)`}} />)}

                {
                    isHovered && <div className="indicator flex flex-col items-center space-y-2">
                        <div className="flex gap-3">
                            <Button variant="contained" color="secondary">
                                <Favorite sx={{color:teal[500]}}/>
                            </Button>
                            <Button variant="contained" color="secondary">
                                <ModeComment sx={{color:teal[500]}}/>
                            </Button>
                        </div>
                    </div>
                }
                
            </div>

            <div className="details pt-3 space-y-1 group-hover-effect rounded-md">
                <div className="name">
                    <h1>Nike</h1>
                    <p>Black Hoodie</p>
                </div>

                <div className="price flex items-center gap-3">
                    <span className="font-semibold text-gray-800">
                        200 MAD
                    </span>
                    <span className="thin-line-through text-gray-400">
                        350 MAD
                    </span>
                    <span className="text-primary-color font-semibold">
                        30%
                    </span>
                </div>
            </div>
        </div>
        </>
     );
}
 
export default ProductCard;