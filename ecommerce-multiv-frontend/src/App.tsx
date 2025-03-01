import React from 'react';
import './App.css';
import Navbar from './customer/components/navbar/Navbar.tsx';
import { ThemeProvider } from '@mui/material';
import customTheme from './theme/CustomTheme.ts';
import Home from './customer/pages/home/Home.tsx';
import Product from './customer/pages/product/Product.tsx';
import ProductDetails from './customer/pages/product_details/ProductDetails.tsx';
import Review from './customer/pages/review/Review.tsx';
import Cart from './customer/pages/cart/Cart.tsx';
import Checkout from './customer/pages/checkout/Checkout.tsx';
import Account from './customer/pages/account/Account.tsx';

function App() {
  return (
      <ThemeProvider theme={customTheme}>
        <div>
          <Navbar/>
          {/* <Home/> */}
          {/* <Product/> */}
          {/* <ProductDetails/> */}
          {/* <Review/> */}
          {/* <Cart/> */}
          {/* <Checkout/> */}
          <Account/>
        </div>
      </ThemeProvider>
      
  );
}

export default App;
