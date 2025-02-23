import { Favorite, ModeComment } from "@mui/icons-material";
import { Button } from "@mui/material";
import { teal } from "@mui/material/colors";
import React from "react";

const SimilarProductsCard = () => {
    return ( 
        <div>
            <div className="group px-4 relative">
                <div className="card">
                     <img
                    className="card-media object-top"
                    src={"https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_B.jpg"} alt=""/>
                    
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
        </div>
     );
}
 
export default SimilarProductsCard;