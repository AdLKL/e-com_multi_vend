import React from 'react';
import './App.css';
import Navbar from './customer/components/navbar/Navbar.tsx';
import { ThemeProvider } from '@mui/material';
import customTheme from './theme/CustomTheme.ts';
import Home from './customer/pages/home/Home.tsx';

function App() {
  return (
      <ThemeProvider theme={customTheme}>
        <div>
          <Navbar/>
          <Home/>
        </div>
      </ThemeProvider>
      
  );
}

export default App;
