import { Radio } from "@mui/material";
import React from "react";

const AddressCard = () => {
    const handleChange = (event: any) => {
        console.log(event.target.checked);
    }
    return ( 
        <div className="p-5 border rounded-md flex">
            <div>
                <Radio checked={true} onChange={handleChange} value="" name="radio-button"/>
            </div>

            <div className="space-y-3 pt-3">
                <h1>Name</h1>
                <p>Address Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                <p><strong>Mobile :</strong> 068754210</p>
            </div>
        </div>
     );
}
 
export default AddressCard;