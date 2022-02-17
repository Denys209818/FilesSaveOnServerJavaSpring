import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import './index.css';


const DefaultLayout = () => 
{
  return (<>
    <Navbar/>
    <Outlet/>
  </>)
}

export default DefaultLayout;