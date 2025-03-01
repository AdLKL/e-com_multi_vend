import { Avatar } from "@mui/material";
import { ElectricBolt } from "@mui/icons-material";
import React from "react";
import { teal } from "@mui/material/colors";

const OrderItem = () => {
    return ( 
        <div className="text-sm bg-white p-5 space-y-4 border rounded-md cursor-pointer">
            <div className="flex items-center gap-5">
                <div>
                    <Avatar sizes="small" sx={{bgcolor: teal[500]}}>
                        <ElectricBolt/>
                    </Avatar>
                </div>

                <div>
                    <h1 className="font-bold text-primary-color">PENDING</h1>
                    <p>Arriving By Mon, 15 Jul</p>
                </div>
            </div>

            <div className="p-5 bg-teal-50 flex gap-3">
                <div>
                    <img className="w-[70px]" 
                    src="https://tokyoflash.com/cdn/shop/products/kwh-revolving-disc-watch-tokyoflash-japan_2048x.jpg?v=1600952685" alt="" />
                </div>

                <div className="w-full space-y-2">
                    <h1 className="font-bold">Seller</h1>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. 
                    Similique blanditiis expedita doloremque recusandae consequatur. 
                    Architecto ducimus expedita quo repellendus accusantium sint eum!</p>
                    <p>
                        <strong>size : </strong>
                        FREE
                    </p>
                </div>
            </div>
        </div>
     );
}
 
export default OrderItem;