import React from "react";
import { MenLevelTwo } from "../../../data/category/level_two/MenLevelTwo.ts";
import { WomenLevelTwo } from "../../../data/category/level_two/WomenLevelTwo.ts";
import { ElectronicsLevelTwo } from "../../../data/category/level_two/ElectronicsLevelTwo.ts";
import { FurnitureLevelTwo } from "../../../data/category/level_two/FurnitureLevelTwo.ts";
import { MenLevelThree } from "../../../data/category/level_three/MenLevelThree.ts";
import { WomenLevelThree } from "../../../data/category/level_three/WomenLevelThree.ts";
import { ElectronicsLevelThree } from "../../../data/category/level_three/ElectronicsLevelThree.ts";
import { FurnitureLevelThree } from "../../../data/category/level_three/FurnitureLevelThree.ts";
import { Box } from "@mui/material";

const categoryTwo:{[key:string]:any[]} = {
    men: MenLevelTwo,
    women: WomenLevelTwo,
    electronics: ElectronicsLevelTwo,
    home_furniture: FurnitureLevelTwo,
}

const categoryThree:{[key:string]:any[]} = {
    men: MenLevelThree,
    women: WomenLevelThree,
    electronics: ElectronicsLevelThree,
    home_furniture: FurnitureLevelThree,
}

const CategorySheet = ({selectedCategory, setShowSheet}: any) => {
    const childCategory = (category: any, parentCategoryId: any) => {
        return category.filter((child: any) => child.parentCategoryId === parentCategoryId)
    }
    return ( 
        <Box sx={
            {zIndex: 1}
        } className="bg-white shadow-lg lg:h-[500px] overflow-y-auto">
            <div className="flex text-sm flex-wrap">
                {
                    categoryTwo[selectedCategory]?.map((item: any, index:any) => 
                    <div className={`p-8 lg:w-[20%] ${index%2===0?"bg-slate-50" : "bg-slate-100"}`}>
                        <p className="text-primary-color mb-5 font-semibold">{item.name}</p>
                        <ul className="space-y-3">

                            {childCategory(categoryThree[selectedCategory], item.categoryId).map((item: any) => <div>
                                <li className="hover: text-black cursor-pointer">
                                    {item.name}
                                </li>
                            </div>)}
                            
                        </ul>
                    </div>)
                }
            </div>

        </Box>
     );
}
 
export default CategorySheet;