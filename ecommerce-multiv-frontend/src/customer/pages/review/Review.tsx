import React from "react";
import ReviewCard from "./ReviewCard";
import { Divider } from "@mui/material";

const Review = () => {
    return ( 
        <div className="p-5 lg:px-20 flex flex-col lg:flow-row gap-20">
            <section className="w-full md:w-1/2 lg:w-[30%] space-y-2">
                <img src="https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_B.jpg" alt="" />
                <div>
                    <div>
                        <p className="font-bold text-xl">Clothes</p>
                        <p className="text-lg text-gray-600">Black Hoodie</p>
                    </div>

                    <div>
                        <div className="price flex items-center gap-3 mt-5 text-2xl">
                            <span className="font-semibold text-gray-800">
                                200 MAD
                            </span>
                            <span className="line-through text-gray-400">
                                350 MAD
                            </span>
                            <span className="text-primary-color font-semibold">
                                30%
                            </span>
                        </div>
                    </div>
                </div>
            </section>

            <section className="space-y-5 w-full">
                {[1,1,1,1,1,1].map((item) => <div className="space-y-3">
                    <ReviewCard/>
                    <Divider/>
                </div>)}
            </section>
        </div>
     );
}
 
export default Review;