import React from 'react';
import { Layout, Menu } from 'antd';
import {Link, Switch} from "react-router-dom";
import { connect } from "react-redux";
import jwtDecode from 'jwt-decode';
import PrivateRoute from "./PrivateRoute";
import ProductsList from "./ProductsList";
import AddProductPage from "../pages/AddProductPage";
import UpdateProductPage from "../pages/UpdateProductPage";

const { Header } = Layout;

const userInfo = state => {
    const token = localStorage.getItem("jwtToken");
    if(token) {
        return jwtDecode(token).sub;
    }
    else
        return state.auth.user;
}

class NavigationBar extends React.Component {
    render() {
        const  username  = this.props.user;
        return (
            <Header>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    style={{ lineHeight: '64px' }} >
                    <Menu.Item key="1"><Link to="/">Home</Link></Menu.Item>
                    { username ? <Menu.Item style={{ float: "right" }} key="2"><Link to="/login">Logout - { username }</Link></Menu.Item> : <Menu.Item style={{ float: "right" }} key="2"><Link to="/login">Login</Link></Menu.Item> }
                    { !username ?  <Menu.Item style={{ float: "right" }} key="3"><Link to="/sign-up">Sign-up</Link></Menu.Item> : null}
                    { username ?  <Menu.Item key="4"><Link to="/products">Products</Link></Menu.Item> : null}
                    { username ?  <Menu.Item key="5"><Link to="/add-product">Add Product</Link></Menu.Item> : null}
                </Menu>
            </Header>
        )
    }
};

const mapStateToProps = state => {
    return {
        user: userInfo(state)
    };
};

export default connect(mapStateToProps)(NavigationBar);