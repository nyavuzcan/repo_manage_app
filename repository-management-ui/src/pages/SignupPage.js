import React, { Component } from 'react'
import Input from "../components/Input"
import {signup} from "../actions/authAction";
import {Alert} from "antd";
import {connect} from "react-redux";



 class SignupPage extends Component {

    state = {
        alertPass:false,
        name:null,
        lastname:null,
        email:null,
        username:null,
        password:null,
        passwordRepeat:null
    }

    onChange = (e) => {
        const {name,value} = e.target

        this.setState({
            [name]:value
        })
    }



    signUpHandler = (e) => {
        e.preventDefault();
        const { dispatch } = this.props;
        const { name,lastname,email,username, password ,passwordRepeat,alert} = this.state;
        if (password!==passwordRepeat){
            this.setState({
                alertPass:true
            })


            }
        else {
            this.setState({
                alertPass:false
            })

            dispatch(signup(name,lastname,email, username,password));
            this.props.history.push("/login")
        }

    }

    render() {
        return (
            <div className="signup-form"  onSubmit={e=>this.signUpHandler(e)}>
            <form>
                <h1 style={{textAlign:"center"}}>Sign up</h1> 
                <Input label="Name" type="text" name="name" className="form-control" onChange={this.onChange} required={true}/>
                <Input label="Surname" type="text" name="lastname" className="form-control" onChange={this.onChange} required={true}/>
                <Input label="Username" type="text" name="username" className="form-control" onChange={this.onChange} required={true}/>
                <Input label="Email" type="text" name="email" className="form-control" onChange={this.onChange} required={true}/>
                <Input label="Password" type="password" name="password" className="form-control" onChange={this.onChange} required={true}/>
                <Input label="Password repeat" type="password" name="passwordRepeat" className="form-control" onChange={this.onChange} required={true}/>
                <div className="button" style={{display:"flex",justifyContent:"center"}}>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </div>
                {this.state.alertPass ? <Alert style={{ textAlign: "center" }} message={"Passwords do not match"} type="error" closable /> : null}
            </form>


        </div>
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


export default connect(mapStateToProps)(SignupPage);