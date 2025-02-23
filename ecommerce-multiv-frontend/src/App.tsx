import React from 'react';
import './App.css';
import Navbar from './customer/components/navbar/Navbar.tsx';
import { ThemeProvider } from '@mui/material';
import customTheme from './theme/CustomTheme.ts';
import Home from './customer/pages/home/Home.tsx';
import Product from './customer/pages/product/Product.tsx';
import ProductDetails from './customer/pages/product_details/ProductDetails.tsx';
import Review from './customer/pages/review/Review.tsx';

function App() {
  return (
      <ThemeProvider theme={customTheme}>
        <div>
          <Navbar/>
          {/* <Home/> */}
          {/* <Product/> */}
          {/* <ProductDetails/> */}
          <Review/>
        </div>
      </ThemeProvider>
      
  );
}

export default App;
