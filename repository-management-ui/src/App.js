import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import { Layout } from 'antd';
import Home from "./components/Home";
import LoginForm from "./pages/LoginForm";
import NavigationBar from "./components/NavigationBar";
import PrivateRoute from "./components/PrivateRoute";
import ProductsList from "./components/ProductsList";
import AddProductPage from "./pages/AddProductPage";
import SignupPage from "./pages/SignupPage";
import UpdateProductPage from "./pages/UpdateProductPage";

const App = (props) => {
    return (
        <Router>
            <div>
                <Layout>
                    <NavigationBar />
                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/login" component={LoginForm}/>
                        <Route exact path="/sign-up" component={SignupPage}/>
                        <PrivateRoute exact path="/products" component={ProductsList}/>
                        <PrivateRoute exact path="/add-product" component={AddProductPage}/>
                        <PrivateRoute exact path="/update-product/:id" component={UpdateProductPage}/>
                    </Switch>
                </Layout>
            </div>
        </Router>
    );
}

export default App;