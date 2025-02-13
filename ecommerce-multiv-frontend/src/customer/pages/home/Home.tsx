import React from "react";
import CategoryGrid from "./categoryGrid/CategoryGrid.tsx";
import ElectricCategory from "./electricCategory/ElectricCategory.tsx";
import Deal from "./deal/Deal.tsx";
import ShopByCategory from "./shopByCategory/ShopByCategory.tsx";
import { Button } from "@mui/material";
import { Storefront } from "@mui/icons-material";

const Home = () => {
    return ( 
        <>
            <div className="space-y-5 lg:space-y-10 relative pb-20">
                <ElectricCategory/>
                <CategoryGrid/>

                <div className="pt-20">
                    <h1 className="text-lg lg:text-4xl font-bold text-primary-color pb-5 lg:pb-10 text-center">TODAY'S DEALS</h1>
                    <Deal/>
                </div>

                <section className="py-20">
                    <h1 className="text-lg lg:text-4xl font-bold text-primary-color pb-5 lg:pb-10 text-center">SHOP BY CATEGORY</h1>
                    <ShopByCategory/>
                </section>

                <section className="lg:px-20 relative h-[200px] lg:h-[450px] object-cover">
                    <img className="w-full h-full" 
                    src="https://img.freepik.com/photos-gratuite/concept-commercial-gros-plan-portrait-jeune-belle-fille-rousse-attrayante-souriant-regardant-camera_1258-118763.jpg?t=st=1739460680~exp=1739464280~hmac=6a9666fd00ee867f206087e3a66cfcd9904cb669ee2065078e595497cc3fc043&w=1380" alt="" />

                    <div className="absolute top-1/2 left-4 lg:left-[15rem] transform -translate-y-1/2 font-semibold lg:text-4xl space-y-3">
                        <h1>Sell your Product</h1>
                        <p className="text-lg md:text-2xl">With our <span className="logo">E-commerce</span></p>
                        <p>Platform</p>
                        <div className="pt-6 flex justify-center">
                            <Button startIcon={<Storefront/>} variant="contained" size="large">
                                Become Seller
                            </Button>
                        </div>
                    </div>
                </section>                
                
            </div>
        </>
     );
}
 
export default Home;