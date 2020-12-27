import React, { Component } from 'react';
import { Form, Input, Button, Alert } from 'antd';
import {LockOutlined, UserAddOutlined} from '@ant-design/icons'

import { connect } from "react-redux";
import {login, logout} from "../actions/authAction";
class LoginForm extends Component {
    componentDidMount() {
        this.props.dispatch(logout());
    }

    state = {
        userName: '',
        password: ''
    }
    handleSubmit = e => {
        e.preventDefault();
        const { dispatch } = this.props;
        const { userName, password } = this.state;
        dispatch(login(userName, password));
    }
    handleChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }
    render() {
        const { isAuthenticated, error, errorMessage } = this.props;
        if (isAuthenticated)
            this.props.history.push('/');
        return(
            <form onSubmit={this.handleSubmit} className="login-form">
                <h1 style={{textAlign:"center"}}>Login</h1>
                <Input prefix={<UserAddOutlined />}
                       placeholder="Username" onChange={this.handleChange} required name="userName" label="Name" type="text" name="userName"/>
                <Input prefix={<LockOutlined />}
                       type="password" name="password" required
                       placeholder="Password" onChange={this.handleChange} label="Password" type="password" name="password"/>
                <div className="button" style={{display:"flex",justifyContent:"center"}}>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </div>
                 {error && !isAuthenticated ? <Alert style={{ textAlign: "center" }} message={"Login Error"} type="error" closable /> : null}
            </form>
        )
    }

}
const mapStateToProps = state => {
    const { isAuthenticated, error, errorMessage, user } = state.auth;
    return {
        isAuthenticated,
        error,
        errorMessage,
        user
    }
}


export default connect(mapStateToProps)(LoginForm);