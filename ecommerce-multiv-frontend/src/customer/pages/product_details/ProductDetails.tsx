import React, { useState } from "react";
import StarIcon from '@mui/icons-material/Star';
import { teal } from "@mui/material/colors";
import { Button, Divider } from "@mui/material";
import { AddShoppingCart, FavoriteBorder, LocalShipping, Remove, Shield, Wallet, WorkspacePremium } from "@mui/icons-material";
import AddIcon from "@mui/icons-material/Add";
import SimilarProducts from "./SimilarProducts";
import ReviewCard from "../review/ReviewCard";

const ProductDetails = () => {
    const [quantity, setQuantity] = useState(1);
    return ( 
        <div className="px-5 lg:px-20 pt-10">
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-10">
                <section className="flex flex-col lg:flex-row gap-5">
                    <div className="w-full lg:w-[15%] flex flex-wrap lg:flex-col gap-3">
                        {[1,1,1,1].map((item) => <img className="lg:w-full w-[50px] cursor-pointer rounded-md" src="https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_C.jpg" alt=""/>)}
                    </div>

                    <div className="w-full lg:w-[85%]">
                        <img className="w-full rounded-md" src="https://photos6.spartoo.eu/photos/242/24250906/24250906_1200_B.jpg" alt="" />
                    </div>
                </section>

                <section>
                    <h1 className="font-bold text-lg text-primary-color">
                        Clothes
                    </h1>
                    <p className="text-gray-500 font-semibold">Men Black Hoodie</p>
                    <div className="flex justify-between items-center py-2 border w-[180px] px-3 mt-5">
                        <div className="flex gap-1 items-center">
                            <span>4</span>
                            <StarIcon sx={{color: teal[500], fontSize:"17px"}} />
                        </div>
                        <Divider orientation="vertical" flexItem/>
                        <span>152 Ratings</span>
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
                        <p className="text-sm">Inclusive of all taxes. Free Shipping above 200 MAD.</p>
                    </div>

                    <div className="mt-7 space-y-3">
                        <div className="flex items-center gap-4">
                            <Shield sx={{color:teal[500]}}/>
                            <p className="">Authentic & Quality Assured</p>
                        </div>
                        <div className="flex items-center gap-4">
                            <WorkspacePremium sx={{color:teal[500]}}/>
                            <p className="">100% money back guarantee</p>
                        </div>
                        <div className="flex items-center gap-4">
                            <LocalShipping sx={{color:teal[500]}}/>
                            <p className="">Free Shipping & Returns</p>
                        </div>
                        <div className="flex items-center gap-4">
                            <Wallet sx={{color:teal[500]}}/>
                            <p className="">Pay on delivery might be available</p>
                        </div>
                    </div>

                    <div className="mt-7 space-y-2">
                        <h1>QUANTITY</h1>
                        <div className="flex items-center gap-2 w-[140px] justify-between">
                            <Button disabled={quantity===1} onClick={() => setQuantity(quantity-1)}>
                                <Remove/>
                            </Button>
                            <span>{quantity}</span>
                            <Button onClick={() => setQuantity(quantity+1)}>
                                <AddIcon/>
                            </Button>
                        </div>
                    </div>

                    <div className="mt-12 flex items-center gap-5">
                        <Button fullWidth variant="contained" startIcon={<AddShoppingCart/>} sx={{py:"1rem"}}>
                            Add to Bag
                        </Button>
                        <Button fullWidth variant="outlined" startIcon={<FavoriteBorder/>} sx={{py:"1rem"}}>
                            Add to Wishlist
                        </Button>
                    </div>

                    <div className="mt-5">
                        <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ab nesciunt vero accusantium eaque 
                        quibusdam sapiente doloremque aliquid amet aliquam id alias assumenda temporibus cumque modi, 
                        repellat dolore eligendi nihil quaerat.</p>
                    </div>

                    <div className="mt-7">
                        <ReviewCard/>
                        <Divider/>

                        
                    </div>
                </section>
            </div>

            <div className="mt-20">
                <h1 className="text-lg font-bold">Similar Products</h1>
                <div className="pt-5">
                    <SimilarProducts/>
                </div>
            </div>
        </div>
     );
}
 
export default ProductDetails;