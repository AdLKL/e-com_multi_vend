import React from "react";

const CategoryGrid = () => {
    return ( 
        <div className="grid gap-4 grid-rows-12 grid-cols-12 lg:h-[600px] px-5 lg:px-20">
            <div className="col-span-3 row-span-12 text-white">
                <img className="w-full h-full object-cover object-top rounded-md" src="https://rukminim2.flixcart.com/image/850/1000/xif0q/shirt/w/q/c/xl-isuel-105-varun-clothes-original-imagxat7ffqwtcy6.jpeg?q=90&crop=false" alt="" />
            </div>
            <div className="col-span-2 row-span-6 text-white">
                <img className="w-full h-full object-cover object-top rounded-md" src="https://www.edsfineimports.com/cdn/shop/articles/mens_clothing_essentials_30s_3024x.jpg?v=1643234762" alt="" />
            </div>
            <div className="col-span-4 row-span-6 text-white">
                <img className="w-full h-full object-cover object-top rounded-md" src="https://www.gap.com/Asset_Archive/GPWeb/content/0030/015/306/assets/FA235245_M_DP_MOB_13.jpg" alt="" />
            </div>
            <div className="col-span-3 row-span-12 text-white">
                <img className="w-full h-full object-cover object-top rounded-md" src="https://image.made-in-china.com/2f0j00RdUlcoAKnakp/Fashion-Children-Clothes-Summer-Cotton-Baby-Girl-Dresses-Comfortable-Girls-Clothing.webp" alt="" />
            </div>
            <div className="col-span-4 row-span-6 text-white">
                <img className="w-full h-full object-cover object-top rounded-md" src="https://media.burford.co.uk/images/SNY04089.jpg_edit.width-640_ln7jm6QxYVkHFHaT.jpg" alt="" />
            </div>
            <div className="col-span-2 row-span-6 text-white">
                <img className="w-full h-full object-cover object-top rounded-md" src="https://images.unsplash.com/photo-1532453288672-3a27e9be9efd?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8ZmFzaGlvbiUyMHN0b3JlfGVufDB8fDB8fHww" alt="" />
            </div>
        </div>
     );
}
 
export default CategoryGrid;