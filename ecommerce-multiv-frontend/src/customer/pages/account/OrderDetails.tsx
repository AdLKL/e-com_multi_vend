import { Box, Button, Divider } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";
import OrderStepper from "./OrderStepper.tsx";
import { Payments } from "@mui/icons-material";

const OrderDetails = () => {
    const navigate = useNavigate();
    return ( 
        <>
        <Box className="space-y-5">
            <section className="flex flex-col gap-5 justify-center items-center">
                <img className="w-[100px]" src={"https://tokyoflash.com/cdn/shop/products/kwh-revolving-disc-watch-tokyoflash-japan_2048x.jpg?v=1600952685"} alt="" />
                <div className="text-sm space-y-1 text-center">
                    <h1 className="font-bold">{"User"}</h1>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis quidem similique numquam quas nemo, enim molestiae.</p>
                    <p><strong>Size: </strong>M</p>
                </div>

                <div>
                    <Button onClick={() => navigate(`/reviews/${5}/create`)}>Write Review</Button>
                </div>
            </section>

            <section className="border p-5">
                <OrderStepper orderStatus={"SHIPPED"}/>
            </section>

            <div className="border p-5">
                <h1 className="font-bold pb-3">Delivery Address</h1>
                <div className="text-sm space-y-2">
                    <div className="flex gap-5 font-medium">
                        <p>{"User"}</p>
                        <Divider flexItem orientation="vertical"/>
                        <p>{468531264}</p>
                    </div>
                    <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit</p>
                </div>
            </div>

            <div className="border space-y-4">
                <div className="flex justify-between text-sm pt-5 px-5">
                    <div className="space-y-1">
                        <p className="font-bold">Total Item Price</p>
                        <p>You saved <span className="text-gray-500 font-medium text-xs">100.00 MAD</span> on this item</p>
                    </div>
                    <p className="font-medium">250 MAD</p>
                </div>

                <div className="px-5">
                    <div className="bg-teal-50 px-5 py-2 text-xs font-medium flex items-center gap-3">
                        <Payments/>
                        <p>Pay On Delivery</p>
                    </div>
                </div>

                <Divider/>
                <div className="px-5 pb-5">
                    <p className="text-xs"><strong>Sold by : </strong>Seller name</p>
                </div>

                <div className="p-10">
                    <Button disabled={true} color="error" sx={{py:"0.7rem"}} variant="outlined" fullWidth>
                        {true ? "order canceled" : "Cancel Order"}
                    </Button>
                </div>
            </div>
        </Box>
        </>
     );
}
 
export default OrderDetails;